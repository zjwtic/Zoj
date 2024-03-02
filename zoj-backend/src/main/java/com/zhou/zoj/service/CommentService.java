package com.zhou.zoj.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.zoj.model.dto.comment.CommentQueryRequest;
import com.zhou.zoj.model.entity.Comment;
import com.zhou.zoj.model.vo.CommentVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 评论服务
 */
public interface CommentService extends IService<Comment> {

    /**
     * 校验
     *
     * @param comment
     * @param add
     */
    void validComment(Comment comment, boolean add);

    /**
     * 获取查询条件
     *
     * @param commentQueryRequest
     * @return
     */
    LambdaQueryWrapper<Comment> getQueryWrapper(CommentQueryRequest commentQueryRequest);


    /**
     * 分页获取评论封装
     *
     * @param commentPage
     * @param request
     * @return
     */
    Page<CommentVO> getCommentVOPage(Page<Comment> commentPage, HttpServletRequest request);


}
