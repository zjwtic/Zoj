package com.zhou.zoj.controller;


import com.zhou.zoj.common.BaseResponse;
import com.zhou.zoj.common.ErrorCode;
import com.zhou.zoj.common.ResultUtils;
import com.zhou.zoj.exception.BusinessException;
import com.zhou.zoj.model.dto.postdislike.PostDislikeAddRequest;
import com.zhou.zoj.model.entity.User;
import com.zhou.zoj.service.PostDislikeService;
import com.zhou.zoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子点踩接口
 */
@RestController
@RequestMapping("/post_dislike")
@Slf4j
public class PostDislikeController {

    @Resource
    private PostDislikeService postDislikeService;

    @Resource
    private UserService userService;

    /**
     * 点踩 / 取消点踩
     *
     * @param postDislikeAddRequest
     * @param request
     * @return resultNum 本次点踩变化数
     */
    @PostMapping("/")
    public BaseResponse<Integer> doDislike(@RequestBody PostDislikeAddRequest postDislikeAddRequest,
                                           HttpServletRequest request) {
        if (postDislikeAddRequest == null || postDislikeAddRequest.getPostId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long postId = postDislikeAddRequest.getPostId();
        int result = postDislikeService.doPostDislike(postId, loginUser);
        return ResultUtils.success(result);
    }

}
