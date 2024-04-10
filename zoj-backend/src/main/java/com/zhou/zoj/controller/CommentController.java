package com.zhou.zoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhou.zoj.common.BaseResponse;
import com.zhou.zoj.common.ErrorCode;
import com.zhou.zoj.common.ResultUtils;
import com.zhou.zoj.exception.ThrowUtils;
import com.zhou.zoj.model.dto.comment.CommentAddRequest;
import com.zhou.zoj.model.dto.comment.CommentQueryRequest;
import com.zhou.zoj.model.entity.Comment;
import com.zhou.zoj.model.entity.User;
import com.zhou.zoj.model.enums.AppHttpCodeEnum;
import com.zhou.zoj.model.vo.CommentVO;
import com.zhou.zoj.service.CommentService;
import com.zhou.zoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 评论接口
 */
@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private UserService userService;


    // region 增删改查

    /**
     * 创建
     *
     * @param commentAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addComment(@RequestBody CommentAddRequest commentAddRequest, HttpServletRequest request) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentAddRequest, comment);
        //注意前端在调用这个发送评论接口时，在请求体是没有向我们传入createTime、createId、updateTime、updateID字段，所以
        //我们这里往后端插入数据时，就会导致上面那行的四个字段没有值
        //为了解决这个问题，我们在huanf-framework工程新增了MyMetaObjectHandler类、修改了Comment类。详细可自己定位去看一下代码

        //限制用户在发送评论时，评论内容不能为空。如果为空就抛出异常
        if (!StringUtils.hasText(comment.getContent())) {
            //AppHttpCodeEnum是我们写的枚举类，CONTENT_NOT_NULL代表提示''
            throw new RuntimeException(AppHttpCodeEnum.CONTENT_NOT_NULL.getMsg());
        }

//        User user = userService.getLoginUser(request);
        User user = userService.getLoginUserPermitNull(request);
        if (user == null) {
            throw new RuntimeException(AppHttpCodeEnum.NEED_LOGIN.getMsg());
        }
        comment.setCreateBy(user.getId());
        comment.setUpdateBy(user.getId());
        Date nowdate = new Date();
        comment.setCreateTime(nowdate);
        comment.setUpdateTime(nowdate);
        //解决了四个字段没有值的情况，就可以直接调用mybatisplus提供的save方法往数据库插入数据(用户发送的评论的各个字段)了
        boolean result = commentService.save(comment);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newPostId = comment.getId();
        //封装响应返回
        return ResultUtils.success(newPostId);
    }

    /**
     * 分页获取列表（封装类）
     *
     * @param commentQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<CommentVO>> listCommentVOByPage(@RequestBody CommentQueryRequest commentQueryRequest,
                                                             HttpServletRequest request) {
        long current = commentQueryRequest.getCurrent();
        long size = commentQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Comment> postPage = commentService.page(new Page<>(current, size),
                commentService.getQueryWrapper(commentQueryRequest));
        return ResultUtils.success(commentService.getCommentVOPage(postPage, request));
    }


}
