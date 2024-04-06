package com.zhou.zoj.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.zoj.common.BaseResponse;
import com.zhou.zoj.common.ErrorCode;
import com.zhou.zoj.common.ResultUtils;
import com.zhou.zoj.constant.CommonConstant;
import com.zhou.zoj.exception.BusinessException;
import com.zhou.zoj.mapper.ContestMapper;
import com.zhou.zoj.mapper.ContestResultMapper;
import com.zhou.zoj.model.dto.contestresult.ContestQuestionData;
import com.zhou.zoj.model.dto.contestresult.ContestResultAddRequest;
import com.zhou.zoj.model.dto.contestresult.ContestResultQueryRequest;
import com.zhou.zoj.model.entity.*;
import com.zhou.zoj.model.enums.ContestResultStatusEnum;

import com.zhou.zoj.model.vo.ContestResultVO;
import com.zhou.zoj.service.ContestResultService;
import com.zhou.zoj.service.ContestService;
import com.zhou.zoj.service.QuestionService;
import com.zhou.zoj.service.UserService;
import com.zhou.zoj.utils.SqlUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import java.util.stream.Collectors;

/**
 * @author 32335
 * @description 针对表【contest_result(比赛结果)】的数据库操作Service实现
 * @createDate 2023-11-23 11:14:59
 */
@Service
public class ContestResultServiceImpl extends ServiceImpl<ContestResultMapper, ContestResult>
        implements ContestResultService {
    @Resource
    private QuestionService questionService;

    @Resource
    private UserService userService;

    //    @Resource
//    private ContestService contestService;
    @Resource
    private ContestMapper contestMapper;

    /**
     * 提交题目
     *
     * @param contestResultAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long addContestResult(ContestResultAddRequest contestResultAddRequest, User loginUser) {

        Long contestId = contestResultAddRequest.getContestId();
        // 判断实体是否存在，根据类别获取实体
        Contest contest = contestMapper.selectById(contestId);
        if (contest == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已提交题目
        long userId = loginUser.getId();
        // 每个用户串行提交题目
        ContestResult contestResult = new ContestResult();
        contestResult.setUserId(userId);
        contestResult.setContestId(contestId);
        QueryWrapper<ContestResult> thumbQueryWrapper = new QueryWrapper<>(contestResult);
        ContestResult oldContestResult = getOne(thumbQueryWrapper);
        if (oldContestResult != null) {
            //提交过就用之前的
            return oldContestResult.getId();
        }

        String selectQuestionIds = contest.getSelectQuestionIds();

        List<String> setlectQuestionIdlist = JSONUtil.toList(selectQuestionIds, String.class);

        List<Question> questionList = questionService.listByIds(setlectQuestionIdlist);

        List<ContestQuestionData> contestQuestionDataList = questionList.stream()
                .map(question -> new ContestQuestionData(question.getId(), question.getTitle()))
                .collect(Collectors.toList());

        //  设置初始状态
        contestResult.setSubmitStatus(ContestResultStatusEnum.UNSUBMITTED.getValue());
        contestResult.setContestExerciseData(JSONUtil.toJsonStr(contestQuestionDataList));
        boolean save = this.save(contestResult);
        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据插入失败");
        }
        return contestResult.getId();
    }

    /**
     * 获取查询包装类
     *
     * @param contestResultQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<ContestResult> getQueryWrapper(ContestResultQueryRequest contestResultQueryRequest) {
        QueryWrapper<ContestResult> queryWrapper = new QueryWrapper<>();
        if (contestResultQueryRequest == null) {
            return queryWrapper;
        }
        Long contestId = contestResultQueryRequest.getContestId();
        Long userId = contestResultQueryRequest.getUserId();
        String sortField = contestResultQueryRequest.getSortField();
        String sortOrder = contestResultQueryRequest.getSortOrder();

        // 拼接查询条件
        queryWrapper.eq(ObjectUtils.isNotEmpty(contestId), "contestId", contestId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public ContestResultVO getContestResultVO(ContestResult contestResult, User loginUser) {
        ContestResultVO contestResultVO = ContestResultVO.objToVo(contestResult);
        return contestResultVO;
    }

    @Override
    public Page<ContestResultVO> getContestResultVOPage(Page<ContestResult> contestResultPage, User loginUser) {
        List<ContestResult> contestResultList = contestResultPage.getRecords();
        Page<ContestResultVO> contestResultVOPage = new Page<>(contestResultPage.getCurrent(), contestResultPage.getSize(),
                contestResultPage.getTotal());
        if (CollectionUtils.isEmpty(contestResultList)) {
            return contestResultVOPage;
        }
        List<ContestResultVO> contestResultVOList = contestResultList.stream()
                .map(contestResult -> getContestResultVO(contestResult, loginUser))
                .collect(Collectors.toList());
        contestResultVOPage.setRecords(contestResultVOList);
        return contestResultVOPage;
    }


    @Override
    public Boolean isUserSubmitted(long contestId, long userId) {
        if (!ObjectUtils.isNotEmpty(contestId) || !ObjectUtils.isNotEmpty(userId)) {
            return false;
        }
        QueryWrapper<ContestResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("contestId", contestId);
        queryWrapper.eq("userId", userId);
        queryWrapper.eq("submitStatus", ContestResultStatusEnum.SUBMITTED.getValue());
        queryWrapper.eq("isDelete", false);
        List<ContestResult> contestResultList = list(queryWrapper);
        return contestResultList.size() != 0;

    }

    @Override
    public Boolean contestResultSubmit(Long userId, Long contestId) {
        Boolean result = false;
        if (!ObjectUtils.isNotEmpty(contestId) || !ObjectUtils.isNotEmpty(userId)) {
            return false;
        }
        QueryWrapper<ContestResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("contestId", contestId);
        queryWrapper.eq("userId", userId);
        queryWrapper.eq("submitStatus", ContestResultStatusEnum.UNSUBMITTED.getValue());
        queryWrapper.eq("isDelete", false);
        ContestResult contestResult = getOne(queryWrapper);
        if (contestResult != null) {
            contestResult.setSubmitStatus(ContestResultStatusEnum.SUBMITTED.getValue());
            result = updateById(contestResult);
        }
        return result;
    }

    @Override
    public List<ContestResultVO> listAllContestResultVO(long contestId) {
        QueryWrapper<ContestResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isDelete", false);
        queryWrapper.eq("contestId", contestId);
        List<ContestResult> contestResultList = list(queryWrapper);
        List<ContestResultVO> selectQuestionVOList = contestResultList.stream()
                .map(contestResult -> ContestResultVO.objToVo(contestResult))
                .collect(Collectors.toList());
        return selectQuestionVOList;
    }
}




