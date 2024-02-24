package com.zhou.zoj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.zoj.model.entity.PostDislike;
import com.zhou.zoj.model.entity.User;


/**
 * 帖子点踩服务
 */
public interface PostDislikeService extends IService<PostDislike> {

    /**
     * 点踩
     *
     * @param postId
     * @param loginUser
     * @return
     */
    int doPostDislike(long postId, User loginUser);

    /**
     * 帖子点踩（内部服务）
     *
     * @param userId
     * @param postId
     * @return
     */
    int doPostDislikeInner(long userId, long postId);
}