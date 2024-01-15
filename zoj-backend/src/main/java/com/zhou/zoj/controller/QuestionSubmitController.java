package com.zhou.zoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhou.zoj.annotation.AuthCheck;
import com.zhou.zoj.common.BaseResponse;
import com.zhou.zoj.common.ErrorCode;
import com.zhou.zoj.common.ResultUtils;
import com.zhou.zoj.constant.UserConstant;
import com.zhou.zoj.exception.BusinessException;
import com.zhou.zoj.judge.JudgeService;
import com.zhou.zoj.model.dto.question.QuestionQueryRequest;
import com.zhou.zoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.zhou.zoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.zhou.zoj.model.entity.Question;
import com.zhou.zoj.model.entity.QuestionSubmit;
import com.zhou.zoj.model.entity.User;
import com.zhou.zoj.model.vo.QuestionSubmitVO;
import com.zhou.zoj.service.QuestionSubmitService;
import com.zhou.zoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return resultNum 提交记录的id
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
            HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitid = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitid);
    }

    /**
     * 分页获取题目提交列表（除了管理员外，普通用户只能看到非答案，提交代码等公开信息）
     *
     * @param questionSubmitQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
                                                                         HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        final User loginUser = userService.getLoginUser(request);
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage,loginUser ));

    }

}
