package com.zhou.zoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.zhou.zoj.annotation.AuthCheck;
import com.zhou.zoj.common.BaseResponse;
import com.zhou.zoj.common.DeleteRequest;
import com.zhou.zoj.common.ErrorCode;
import com.zhou.zoj.common.ResultUtils;
import com.zhou.zoj.constant.UserConstant;
import com.zhou.zoj.exception.BusinessException;
import com.zhou.zoj.exception.ThrowUtils;

import com.zhou.zoj.model.dto.contest.ContestAddRequest;
import com.zhou.zoj.model.dto.contest.ContestQueryRequest;
import com.zhou.zoj.model.dto.contest.ContestUpdateRequest;
import com.zhou.zoj.model.dto.question.JudgeCase;
import com.zhou.zoj.model.dto.question.JudgeConfig;
import com.zhou.zoj.model.entity.Contest;
import com.zhou.zoj.model.entity.User;

import com.zhou.zoj.model.vo.ContestVO;
import com.zhou.zoj.service.ContestService;
import com.zhou.zoj.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 比赛接口
 */
@RestController
@RequestMapping("/contest")
@Slf4j
public class ContestController {

    @Resource
    private UserService userService;

    @Resource
    private ContestService contestService;

    private final static Gson GSON = new Gson();

    // region 增删改查

    /**
     * 创建
     *
     * @param contestAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addContest(@RequestBody ContestAddRequest contestAddRequest, HttpServletRequest request) {
        if (contestAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Contest contest = new Contest();
        BeanUtils.copyProperties(contestAddRequest, contest);


        List<String> selectQuestionIds = contestAddRequest.getSelectQuestionIds();
        if (selectQuestionIds != null) {
            contest.setSelectQuestionIds(GSON.toJson(selectQuestionIds));
        }
        contestService.validContest(contest, true);
        User loginUser = userService.getLoginUser(request);
        contest.setUserId(loginUser.getId());
        boolean result = contestService.save(contest);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newContestId = contest.getId();
        return ResultUtils.success(newContestId);

    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteContest(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        Contest oldContest = contestService.getById(id);
        ThrowUtils.throwIf(oldContest == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldContest.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = contestService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 分页获取比赛列表（仅管理员）
     *
     * @param contestQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<Contest>> listContestByPage(@RequestBody ContestQueryRequest contestQueryRequest,
                                                         HttpServletRequest request) {
        long current = contestQueryRequest.getCurrent();
        long size = contestQueryRequest.getPageSize();
        Page<Contest> contestPage = contestService.page(new Page<>(current, size),
                contestService.getQueryWrapper(contestQueryRequest));
        return ResultUtils.success(contestPage);
    }

    /**
     * 更新（仅管理员）
     *
     * @param contestUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateContest(@RequestBody ContestUpdateRequest contestUpdateRequest) {
        if (contestUpdateRequest == null || contestUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Contest contest = new Contest();
        BeanUtils.copyProperties(contestUpdateRequest, contest);
        List<String> selectQuestionIds = contestUpdateRequest.getSelectQuestionIds();
        if (selectQuestionIds != null) {
            contest.setSelectQuestionIds(GSON.toJson(selectQuestionIds));
        }
        // 参数校验
        contestService.validContest(contest, false);
        long id = contestUpdateRequest.getId();
        // 判断是否存在
        Contest oldContest = contestService.getById(id);
        ThrowUtils.throwIf(oldContest == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = contestService.updateById(contest);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Contest> getContestById(long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Contest contest = contestService.getById(id);
        if (contest == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可查看自己
        User loginUser = userService.getLoginUser(request);
        if (!contest.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return ResultUtils.success(contest);
    }


    /**
     * 根据 id 获取(脱敏)
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<ContestVO> getContestVOById(long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Contest contest = contestService.getById(id);
        if (contest == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(contestService.getContestVO(contest, request));
    }

    /**
     * 分页获取列表（封装类）
     *
     * @param contestQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<ContestVO>> listContestVOByPage(@RequestBody ContestQueryRequest contestQueryRequest,
                                                             HttpServletRequest request) {
        long current = contestQueryRequest.getCurrent();
        long size = contestQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Contest> contestPage = contestService.page(new Page<>(current, size),
                contestService.getQueryWrapper(contestQueryRequest));
        return ResultUtils.success(contestService.getContestVOPage(contestPage, request));
    }


}
