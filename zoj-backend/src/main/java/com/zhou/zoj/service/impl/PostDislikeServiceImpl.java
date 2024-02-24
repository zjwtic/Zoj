package com.zhou.zoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.zoj.common.ErrorCode;
import com.zhou.zoj.exception.BusinessException;
import com.zhou.zoj.mapper.PostDislikeMapper;
import com.zhou.zoj.model.entity.Post;
import com.zhou.zoj.model.entity.PostDislike;
import com.zhou.zoj.model.entity.User;
import com.zhou.zoj.service.PostDislikeService;
import com.zhou.zoj.service.PostService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * 帖子点踩服务实现
 *
 */
@Service
public class PostDislikeServiceImpl extends ServiceImpl<PostDislikeMapper, PostDislike>
        implements PostDislikeService {

    @Resource
    private PostService postService;

    /**
     * 点踩
     *
     * @param postId
     * @param loginUser
     * @return
     */
    @Override
    public int doPostDislike(long postId, User loginUser) {
        // 判断实体是否存在，根据类别获取实体
        Post post = postService.getById(postId);
        if (post == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已点踩
        long userId = loginUser.getId();
        // 每个用户串行点踩
        // 锁必须要包裹住事务方法
        PostDislikeService postDislikeService = (PostDislikeService) AopContext.currentProxy();
        synchronized (String.valueOf(userId).intern()) {
            return postDislikeService.doPostDislikeInner(userId, postId);
        }
    }

    /**
     * 封装了事务的方法
     *
     * @param userId
     * @param postId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doPostDislikeInner(long userId, long postId) {
        PostDislike postDislike = new PostDislike();
        postDislike.setUserId(userId);
        postDislike.setPostId(postId);
        QueryWrapper<PostDislike> dislikeQueryWrapper = new QueryWrapper<>(postDislike);
        PostDislike oldPostDislike = this.getOne(dislikeQueryWrapper);
        boolean result;
        // 已点踩
        if (oldPostDislike != null) {
            result = this.remove(dislikeQueryWrapper);
            if (result) {
                // 点踩数 - 1
                result = postService.update()
                        .eq("id", postId)
                        .gt("dislikeNum", 0)
                        .setSql("dislikeNum = dislikeNum - 1")
                        .update();
                return result ? -1 : 0;
            } else {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        } else {
            // 未点踩
            result = this.save(postDislike);
            if (result) {
                // 点踩数 + 1
                result = postService.update()
                        .eq("id", postId)
                        .setSql("dislikeNum = dislikeNum + 1")
                        .update();
                return result ? 1 : 0;
            } else {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        }
    }

}




