package com.zhou.zoj.controller;

import com.zhou.zoj.common.BaseResponse;
import com.zhou.zoj.common.DeleteRequest;
import com.zhou.zoj.common.ErrorCode;
import com.zhou.zoj.common.ResultUtils;

import com.zhou.zoj.exception.BusinessException;
import com.zhou.zoj.exception.ThrowUtils;
import com.zhou.zoj.judge.codesandbox.model.JudgeInfo;

import com.zhou.zoj.model.dto.contestresult.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.zhou.zoj.model.entity.*;
import com.zhou.zoj.model.entity.ContestResult;
import com.zhou.zoj.model.enums.ContestResultStatusEnum;
import com.zhou.zoj.model.enums.JudgeInfoMessageEnum;
import com.zhou.zoj.model.vo.*;
import com.zhou.zoj.model.vo.ContestResultVO;
import com.zhou.zoj.service.ContestResultService;
import com.zhou.zoj.service.QuestionSubmitService;
import com.zhou.zoj.service.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

    @Resource
    private QuestionSubmitService questionSubmitService;


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
//        // 仅本人或管理员可查看自己
//        User loginUser = userService.getLoginUser(request);
//        if (!contestResult.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
//            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
//        }
        return ResultUtils.success(contestResult);
    }


    /**
     * 根据 id 获取 vo
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<ContestResultVO> getContestResultVOById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ContestResult contestResult = contestResultService.getById(id);
        if (contestResult == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(ContestResultVO.objToVo(contestResult));
    }
    // todo 更新contestResult 需要动态的去更新    他会调用 question do_submit  基于他的结果去动态更新比赛结果

    /**
     * 更新
     *
     * @param contestResultUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<UpdateContestResultVO> updateContestResult(@RequestBody ContestResultUpdateRequest contestResultUpdateRequest, HttpServletRequest request) {
        if (contestResultUpdateRequest == null || contestResultUpdateRequest.getContestResultId() <= 0 || contestResultUpdateRequest.getQuestionSubmitId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ContestResult contestResult = contestResultService.getById(contestResultUpdateRequest.getContestResultId());
        if (contestResult == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        //已经提交了 就禁止更改了
        if (contestResult.getSubmitStatus().equals(ContestResultStatusEnum.SUBMITTED.getValue())) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR);
        }

        try {
            Thread.sleep(3000l); // 等待3秒
        } catch (InterruptedException e) {
            e.printStackTrace(); // 恢复中断状态
            // 在这里你可以选择退出循环或者重新抛出异常
        }
        QuestionSubmit questionSubmit = questionSubmitService.getById(contestResultUpdateRequest.getQuestionSubmitId());
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        UpdateContestResultVO updateContestResultVO = new UpdateContestResultVO();
        updateContestResultVO.setIsUpdateSuccess(false);
        updateContestResultVO.setThisTimeIsQuestionAccepted(false);
        updateContestResultVO.setThisTimeBestTimeConsume(0l);
        int lastTime = 0;

        while (questionSubmit.getJudgeInfo().equals("{}") && lastTime <= 2) {
            // 等待一段时间再尝试，避免过于频繁的请求
            try {
                Thread.sleep(3000l); // 等待3秒
                lastTime += 1;
            } catch (InterruptedException e) {// 恢复中断状态
                e.printStackTrace();
                // 在这里你可以选择退出循环或者重新抛出异常
                break;
            }
            questionSubmit = questionSubmitService.getById(contestResultUpdateRequest.getQuestionSubmitId());
        }


        User loginUser = userService.getLoginUser(request);
        ContestResultVO contestResultVO = contestResultService.getContestResultVO(contestResult, loginUser);
        QuestionSubmitVO questionSubmitVO = questionSubmitService.getQuestionSubmitVO(questionSubmit, loginUser);

        JudgeInfo judgeInfo = questionSubmitVO.getJudgeInfo();
        Long questionId = questionSubmitVO.getQuestionId();
        Boolean isFind = false;

        List<ContestQuestionData> contestQuestionDatas = contestResultVO.getContestQuestionDatas();
        for (ContestQuestionData contestQuestionData : contestQuestionDatas) {
            if (contestQuestionData.getQuestionId().equals(questionId)) {
                //如果成功 则 设置 状态
                if (JudgeInfoMessageEnum.ACCEPTED.getValue().equals(judgeInfo.getMessage())) {
                    updateContestResultVO.setThisTimeIsQuestionAccepted(true);
                    contestQuestionData.setIsAccepted(true);
                    Long bestTimeConsume = contestQuestionData.getTimeConsume();
                    if (bestTimeConsume == 0) {
                        Long nowTimeConsume = judgeInfo.getTime();
                        updateContestResultVO.setThisTimeBestTimeConsume(nowTimeConsume);
                        contestQuestionData.setTimeConsume(nowTimeConsume);

                    }
                    // 鼓励 写出更优秀的算法
                    else {
                        Long nowTimeConsume = judgeInfo.getTime();
                        Long lower = Math.min(bestTimeConsume, nowTimeConsume);
                        updateContestResultVO.setThisTimeBestTimeConsume(lower);
                        contestQuestionData.setTimeConsume(lower);
                    }

                }
                //已经通过了 就不会 记录编译错误了
                else if (!contestQuestionData.getIsAccepted()) {
                    contestQuestionData.setErrorNum(contestQuestionData.getErrorNum() + 1);
                }
                isFind = true;
                break;
            }

        }
        if (isFind) {

            contestResultVO.setContestQuestionDatas(contestQuestionDatas);
            ContestResult updatecontestResult = ContestResultVO.voToObj(contestResultVO);
            Boolean result = contestResultService.updateById(updatecontestResult);
            updateContestResultVO.setIsUpdateSuccess(result);
            return ResultUtils.success(updateContestResultVO);
        }
        return ResultUtils.success(updateContestResultVO);
    }


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
        return ResultUtils.success(contestResultService.getContestResultVOPage(contestResultPage, loginUser));

    }


    /**
     * 比赛结果提交
     *
     * @param contestResultSubmitRequest
     * @param request
     * @return
     */
    @PostMapping("/submit")
    public BaseResponse<Boolean> contestResultSubmit(@RequestBody ContestResultSubmitRequest contestResultSubmitRequest,
                                                     HttpServletRequest request) {

        User loginUser = userService.getLoginUser(request);


        return ResultUtils.success(contestResultService.contestResultSubmit(loginUser.getId(), contestResultSubmitRequest.getContestId()));


    }

    /**
     * 获取所有比赛成绩信息
     *
     * @param contestId
     * @return
     */
    @PostMapping("/list/all/vo")
    public BaseResponse<List<ContestResultPointVO>> listAllContestResultPointVO(long contestId) {
        if (contestId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<ContestResultPointVO> contestResultVOList = contestResultService.listAllContestResultPointVO(contestId);
        return ResultUtils.success(contestResultVOList);
    }

    /**
     * 获取所有比赛题目通过信息
     *
     * @param contestId
     * @return
     */
    @PostMapping("/get/Accepted")
    public BaseResponse<List<ContestQuestionAccepted>> getQuestionAcceptedNum(long contestId) {
        if (contestId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Boolean firstGet = true;
        List<ContestResultVO> contestResultVOList = contestResultService.listAllContestResultVO(contestId);
        Map<Long, Integer> questionAcceptedNumMap = new HashMap<>();
        Map<Long, String> questionQuestionTitleMap = new HashMap<>();
        for (ContestResultVO contestResultVO : contestResultVOList) {
            for (ContestQuestionData contestQuestionData : contestResultVO.getContestQuestionDatas()) {
                Long questionId = contestQuestionData.getQuestionId();
                Integer acceptedNum = questionAcceptedNumMap.getOrDefault(questionId, 0);
                if (contestQuestionData.getIsAccepted()) {
                    questionAcceptedNumMap.put(questionId, acceptedNum + 1);
                } else if (!questionAcceptedNumMap.containsKey(questionId)) {
                    questionAcceptedNumMap.put(questionId, 0);
                }
                if (firstGet) {
                    questionQuestionTitleMap.put(questionId, contestQuestionData.getTitle());
                }
            }
            firstGet = false;
        }

        List<ContestQuestionAccepted> contestQuestionAcceptedList = questionAcceptedNumMap.entrySet().stream()
                .map(item -> new ContestQuestionAccepted(item.getKey(), item.getValue(), questionQuestionTitleMap.get(item.getKey())))
                .collect(Collectors.toList());

        return ResultUtils.success(contestQuestionAcceptedList);
    }

}
