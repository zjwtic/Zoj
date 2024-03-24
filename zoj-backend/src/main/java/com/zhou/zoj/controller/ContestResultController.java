package com.zhou.zoj.controller;

import com.zhou.zoj.annotation.AuthCheck;
import com.zhou.zoj.common.BaseResponse;
import com.zhou.zoj.common.DeleteRequest;
import com.zhou.zoj.common.ErrorCode;
import com.zhou.zoj.common.ResultUtils;
import com.zhou.zoj.constant.UserConstant;
import com.zhou.zoj.exception.BusinessException;
import com.zhou.zoj.exception.ThrowUtils;
import com.zhou.zoj.model.dto.contestresult.ContestResultAddRequest;
import com.zhou.zoj.model.dto.contestresult.ContestResultQueryRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhou.zoj.model.dto.question.JudgeCase;
import com.zhou.zoj.model.dto.question.JudgeConfig;
import com.zhou.zoj.model.entity.ContestResult;
import com.zhou.zoj.model.entity.ContestResult;
import com.zhou.zoj.model.entity.User;
import com.zhou.zoj.model.vo.ContestResultVO;
import com.zhou.zoj.model.vo.ContestResultVO;
import com.zhou.zoj.service.ContestResultService;
import com.zhou.zoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 比赛结果接口
 */
@RestController
@RequestMapping("/contest_result")
@Slf4j
public class ContestResultController {

    @Resource
    private ContestResultService contestResultService;

    @Resource
    private UserService userService;

    /**
     * 进行比赛
     *
     * @param contestResultAddRequest
     * @param request
     * @return resultNum 提交记录的id
     */
    @PostMapping("add/")
    public BaseResponse<Long> addContestResult(@RequestBody ContestResultAddRequest contestResultAddRequest,
                                               HttpServletRequest request) {
        if (contestResultAddRequest == null || contestResultAddRequest.getContestId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能提交
        final User loginUser = userService.getLoginUser(request);
        long contestResultid = contestResultService.addContestResult(contestResultAddRequest, loginUser);
        return ResultUtils.success(contestResultid);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteContestResult(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        ContestResult oldContestResult = contestResultService.getById(id);
        ThrowUtils.throwIf(oldContestResult == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldContestResult.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = contestResultService.removeById(id);
        return ResultUtils.success(b);
    }



    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<ContestResult> getContestResultById(long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ContestResult contestResult = contestResultService.getById(id);
        if (contestResult == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可查看自己
        User loginUser = userService.getLoginUser(request);
        if (!contestResult.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return ResultUtils.success(contestResult);
    }

    // todo 更新contestResult 需要动态的去更新    他会调用 question do_submit  基于他的结果去动态更新比赛结果
    
    /**
     * 分页获取比赛结果列表
     *
     * @param contestResultQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<ContestResultVO>> listContestResultByPage(@RequestBody ContestResultQueryRequest contestResultQueryRequest,
                                                                         HttpServletRequest request) {
        long current = contestResultQueryRequest.getCurrent();
        long size = contestResultQueryRequest.getPageSize();
        final User loginUser = userService.getLoginUser(request);
        Page<ContestResult> contestResultPage = contestResultService.page(new Page<>(current, size),
                contestResultService.getQueryWrapper(contestResultQueryRequest));
        return ResultUtils.success(contestResultService.getContestResultVOPage(contestResultPage,loginUser ));

    }

}
