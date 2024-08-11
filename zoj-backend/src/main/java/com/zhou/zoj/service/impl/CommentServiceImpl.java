package com.zhou.zoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.zoj.common.ErrorCode;
import com.zhou.zoj.exception.BusinessException;
import com.zhou.zoj.exception.ThrowUtils;
import com.zhou.zoj.mapper.CommentMapper;
import com.zhou.zoj.model.dto.comment.CommentQueryRequest;
import com.zhou.zoj.model.entity.Comment;
import com.zhou.zoj.model.entity.User;
import com.zhou.zoj.model.vo.CommentVO;
import com.zhou.zoj.service.CommentService;
import com.zhou.zoj.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.zhou.zoj.constant.SystemCanstants.COMMENT_ROOT;

/**
 * @author 32335
 * @description 针对表【comment(评论)】的数据库操作Service实现
 * @createDate 2023-11-23 11:14:17
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

    @Resource
    private UserService userService;


    @Override
    public void validComment(Comment comment, boolean add) {
        if (comment == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String content = comment.getContent();


        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(content), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验

        if (StringUtils.isNotBlank(content) && content.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }

    }

    /**
     * 获取查询包装类
     *
     * @param commentQueryRequest
     * @return
     */
    @Override
    public LambdaQueryWrapper<Comment> getQueryWrapper(CommentQueryRequest commentQueryRequest) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        if (commentQueryRequest == null) {
            return queryWrapper;
        }

        Long postId = commentQueryRequest.getPostId();

        // 拼接查询条件

        //对articleId进行判断，作用是得到指定的文章
        //对articleId进行判断，作用是得到指定的文章。如果是文章评论，才会判断articleId，避免友链评论判断articleId时出现空指针
        queryWrapper.eq(Comment::getPostId, postId);

        //对评论区的某条评论的rootID进行判断，如果为-1，就表示是根评论。SystemCanstants是我们写的解决字面值的类
        queryWrapper.eq(Comment::getRootId, COMMENT_ROOT);
        queryWrapper.orderByAsc(Comment::getCreateTime);

        return queryWrapper;
    }


    @Override
    public Page<CommentVO> getCommentVOPage(Page<Comment> commentPage, HttpServletRequest request) {
        List<Comment> commentList = commentPage.getRecords();
        Page<CommentVO> commentVOPage = new Page<>(commentPage.getCurrent(), commentPage.getSize(), commentPage.getTotal());
        if (CollectionUtils.isEmpty(commentList)) {
            return commentVOPage;
        }
        //调用下面那个方法
        List<CommentVO> commentVOList = xxToCommentList(commentPage.getRecords());

        //查询子评论
        commentVOList = commentVOList.stream().map(commentVO -> commentVO.setChildren(getChildren(commentVO.getId())))
                .collect(Collectors.toList());
        commentVOPage.setRecords(commentVOList);
        return commentVOPage;
    }

    private List<CommentVO> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);
        List<CommentVO> commentVos = xxToCommentList(comments);
        return commentVos;
    }

    //
    //封装响应返回。CommentVO、BeanCopyUtils、ResponseResult、PageVo是我们写的类
    private List<CommentVO> xxToCommentList(List<Comment> list) {
        //获取评论区的所有评论
//        List<CommentVO> commentVos = BeanCopyUtils.copyBeanList(list, CommentVO.class);
        List<CommentVO> commentVos = list.stream().map(comment -> CommentVO.objToVo(comment)).collect(Collectors.toList());
        //遍历。由于封装响应好的数据里面没有username字段，所以我们还不能返回给前端。这个遍历就是用来得到username字段
        for (CommentVO commentVo : commentVos) {
            //
            //需要根据commentVo类里面的createBy字段，然后用createBy字段去查询user表的nickname字段(子评论的用户昵称)
            User user = userService.getById(commentVo.getCreateBy());
            String nickName = user.getUserName();
            String userAvatar = user.getUserAvatar();
            //然后把nickname字段(发这条子评论的用户昵称)的数据赋值给commentVo类的username字段
            commentVo.setUsername(nickName);
            commentVo.setUserAvatar(userAvatar);

            //查询根评论的用户昵称。怎么判断是根评论的用户呢，判断getToCommentUserId为1，就表示这条评论是根评论
            if (commentVo.getToCommentUserId() != -1) {
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getUserName();
                //然后把nickname字段(发这条根评论的用户昵称)的数据赋值给commentVo类的toCommentUserName字段
                commentVo.setToCommentUserName(toCommentUserName);
            }

        }

        //返回给前端
        return commentVos;
    }

}




