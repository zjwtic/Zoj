package com.zhou.zoj.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.zhou.zoj.common.DeleteRequest;
import com.zhou.zoj.common.ErrorCode;
import com.zhou.zoj.constant.CommonConstant;
import com.zhou.zoj.exception.BusinessException;
import com.zhou.zoj.exception.ThrowUtils;
import com.zhou.zoj.mapper.ContestMapper;
import com.zhou.zoj.model.dto.contest.ContestAddRequest;
import com.zhou.zoj.model.dto.contest.ContestQueryRequest;
import com.zhou.zoj.model.dto.contest.ContestUpdateRequest;
import com.zhou.zoj.model.entity.Contest;
import com.zhou.zoj.model.entity.Question;
import com.zhou.zoj.model.entity.User;
import com.zhou.zoj.model.vo.ContestResultPointVO;
import com.zhou.zoj.model.vo.ContestVO;
import com.zhou.zoj.model.vo.UserVO;
import com.zhou.zoj.service.ContestResultService;
import com.zhou.zoj.service.ContestService;
import com.zhou.zoj.service.QuestionService;
import com.zhou.zoj.service.UserService;
import com.zhou.zoj.utils.ScheduledTaskManager;
import com.zhou.zoj.utils.SqlUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 32335
 * @description 针对表【contest(比赛)】的数据库操作Service实现
 * @createDate 2023-11-23 11:14:17
 */
@Service
public class ContestServiceImpl extends ServiceImpl<ContestMapper, Contest>
        implements ContestService {
    private final static Gson GSON = new Gson();

    private final static ScheduledTaskManager manager = new ScheduledTaskManager();

    @Resource
    private UserService userService;

    @Resource
    private QuestionService questionService;


    @Resource
    private ContestResultService contestResultService;


    @Override
    public void validContest(Contest contest, boolean add) {
        if (contest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String selectQuestionIds = contest.getSelectQuestionIds();
        String contestName = contest.getContestName();
        String contestDescription = contest.getContestDescription();
        Date startTime = contest.getStartTime();
        Date endTime = contest.getEndTime();
        Integer duration = contest.getDuration();

        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(contestName, contestDescription, selectQuestionIds), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(contestName) && contestName.length() > 80) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "比赛标题过长");
        }
        if (StringUtils.isNotBlank(contestDescription) && contestDescription.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "比赛描述过长");
        }
        if (duration > 36000) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "时间设置不合理，太长了");
        }

        if (startTime == null || endTime == null || startTime.after(endTime)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "比赛开始结束时间设置不合理");
        }
        List<String> setlectQuestionIdlist = JSONUtil.toList(contest.getSelectQuestionIds(), String.class);
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isDelete", false);
        List<Question> questionList = questionService.list(queryWrapper);

        List<String> questionIdList = questionList.stream().map(question -> question.getId().toString()).collect(Collectors.toList());
        for (String setlectQuestionId : setlectQuestionIdlist) {
            if (!questionIdList.contains(setlectQuestionId)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "比赛选择题目id不存在");
            }
        }
    }

    /**
     * 获取查询包装类
     *
     * @param contestQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<Contest> getQueryWrapper(ContestQueryRequest contestQueryRequest) {
        QueryWrapper<Contest> queryWrapper = new QueryWrapper<>();
        if (contestQueryRequest == null) {
            return queryWrapper;
        }

        Long id = contestQueryRequest.getId();
        Long userId = contestQueryRequest.getUserId();
        String contestName = contestQueryRequest.getContestName();
        String contestDescription = contestQueryRequest.getContestDescription();
        Date startTime = contestQueryRequest.getStartTime();
        Date endTime = contestQueryRequest.getEndTime();
        Integer duration = contestQueryRequest.getDuration();
        String sortField = contestQueryRequest.getSortField();
        String sortOrder = contestQueryRequest.getSortOrder();


        // 拼接查询条件
        queryWrapper.like(StringUtils.isNotBlank(contestName), "contestName", contestName);
        queryWrapper.like(StringUtils.isNotBlank(contestDescription), "contestDescription", contestDescription);
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(startTime), "startTime", startTime);
        queryWrapper.eq(ObjectUtils.isNotEmpty(endTime), "endTime", endTime);
        queryWrapper.eq(ObjectUtils.isNotEmpty(duration), "duration", duration);
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public ContestVO getContestVO(Contest contest, HttpServletRequest request) {
        ContestVO contestVO = ContestVO.objToVo(contest);
        long contestId = contest.getId();
        // 1. 关联查询用户信息
        Long userId = contest.getUserId();
        User user = null;
        if (userId != null && userId > 0) {
            user = userService.getById(userId);
        }
        UserVO userVO = userService.getUserVO(user);
        contestVO.setUserVO(userVO);

////        注意上面 是创建比赛的用户信息id 我们需要的是当前用户id
        // todo contestVO 可能需要 user是否做了contest信息    可能是bool  也可能是 contestResultId
//        todo bool
        User loginUser = userService.getLoginUserPermitNull(request);
        Boolean isParticipated = false;
        if (loginUser != null) {
            isParticipated = contestResultService.isUserSubmitted(contestId, loginUser.getId());
        }
        contestVO.setIsParticipated(isParticipated);
        return contestVO;
    }

    @Override
    public Page<ContestVO> getContestVOPage(Page<Contest> contestPage, HttpServletRequest request) {
        List<Contest> contestList = contestPage.getRecords();
        Page<ContestVO> contestVOPage = new Page<>(contestPage.getCurrent(), contestPage.getSize(), contestPage.getTotal());
        if (CollectionUtils.isEmpty(contestList)) {
            return contestVOPage;
        }
        // 1. 关联查询用户信息
        //找到所有contest的userid  可能有相同的 所以用set去重
        //然后根据 userid找到所有user  同时 变成  map   键为 userid  值为user  （可能有多个？ 其实不太可能）
        Set<Long> userIdSet = contestList.stream().map(Contest::getUserId).collect(Collectors.toSet());
        Map<Long, List<User>> userIdUserListMap = userService.listByIds(userIdSet).stream()
                .collect(Collectors.groupingBy(User::getId));

        User loginUser = userService.getLoginUserPermitNull(request);
        // 填充信息
        List<ContestVO> contestVOList = contestList.stream().map(contest -> {
            ContestVO contestVO = ContestVO.objToVo(contest);
            Long userId = contest.getUserId();
            User user = null;
            //list 有多个取第一个   其实基本上只有一个
            if (userIdUserListMap.containsKey(userId)) {
                user = userIdUserListMap.get(userId).get(0);
            }
            contestVO.setUserVO(userService.getUserVO(user));

            // todo contestVO 可能需要 user是否做了contest信息    可能是bool  也可能是 contestResultId
            // todo bool
            Long contestId = contest.getId();

            Boolean isParticipated = false;
            if (loginUser != null) {
                isParticipated = contestResultService.isUserSubmitted(contestId, loginUser.getId());
            }
            contestVO.setIsParticipated(isParticipated);
            return contestVO;
        }).collect(Collectors.toList());
        contestVOPage.setRecords(contestVOList);
        return contestVOPage;
    }

    @Override
    public Long addContest(ContestAddRequest contestAddRequest, User loginUser) {

        Contest contest = new Contest();
        BeanUtils.copyProperties(contestAddRequest, contest);
        List<String> selectQuestionIds = contestAddRequest.getSelectQuestionIds();
        if (selectQuestionIds != null) {
            contest.setSelectQuestionIds(GSON.toJson(selectQuestionIds));
        }
        validContest(contest, true);

        contest.setUserId(loginUser.getId());
        boolean result = save(contest);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newContestId = contest.getId();

        // 安排任务
        Runnable task = () -> {
            System.out.println("比赛contestId:" + newContestId + "结束，开始发放奖励");
            userAddPoints(newContestId);

        };
        manager.scheduleTask(newContestId, contestAddRequest.getEndTime(), task);
        return newContestId;
    }

    @Override
    public boolean deleteContest(DeleteRequest deleteRequest, User user, HttpServletRequest request) {

        long id = deleteRequest.getId();
        // 判断是否存在
        Contest oldContest = getById(id);
        ThrowUtils.throwIf(oldContest == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldContest.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = removeById(id);
        manager.cancelTask(id);
        return b;
    }

    @Override
    public boolean updateContest(ContestUpdateRequest contestUpdateRequest) {
        Contest contest = new Contest();
        BeanUtils.copyProperties(contestUpdateRequest, contest);
        List<String> selectQuestionIds = contestUpdateRequest.getSelectQuestionIds();
        if (selectQuestionIds != null) {
            contest.setSelectQuestionIds(GSON.toJson(selectQuestionIds));
        }
        // 参数校验
        validContest(contest, false);
        long id = contestUpdateRequest.getId();
        // 判断是否存在
        Contest oldContest = getById(id);
        ThrowUtils.throwIf(oldContest == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = updateById(contest);
        // 安排任务
        Runnable task = () -> {
            System.out.println("比赛contestId:" + id + "结束，开始发放奖励");
            userAddPoints(id);

        };
        manager.updateTask(id, contestUpdateRequest.getEndTime(), task);
        return result;
    }


    private void userAddPoints(Long newContestId) {

        //          //  获取所有排名
        List<ContestResultPointVO> contestResultPointVOList = contestResultService.listAllContestResultPointVO(newContestId);

        if (contestResultPointVOList == null || contestResultPointVOList.size() == 0) {
            return;
        }
        Long firstReworderId = null;
        Long SecondReworderId = null;
        Long ThirdReworderId = null;
        List<Long> topTenReworderId = new ArrayList();
        List<Long> otherReworderId = new ArrayList();
        int size = contestResultPointVOList.size();
        if (size > 0) {
            firstReworderId = contestResultPointVOList.get(0).getUserId();
        }
        if (size > 1) {
            SecondReworderId = contestResultPointVOList.get(1).getUserId();
        }
        if (size > 2) {
            ThirdReworderId = contestResultPointVOList.get(2).getUserId();
        }
        int toIndex = Math.min(10, size);
        if (size > 3) {
            topTenReworderId = contestResultPointVOList.subList(3, toIndex).stream()
                    .map(contestResultPointVO -> contestResultPointVO.getUserId()).collect(Collectors.toList());
        }
        if (size > 10) {
            otherReworderId = contestResultPointVOList.subList(10, size).stream()
                    .map(contestResultPointVO -> contestResultPointVO.getUserId()).collect(Collectors.toList());
        }

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        // 创建 UpdateWrapper 对象
        if (firstReworderId != null) {
            updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", firstReworderId).setSql("points = points + 100");

            boolean result1 = userService.update(null, updateWrapper);
            ThrowUtils.throwIf(!result1, ErrorCode.OPERATION_ERROR);
        }
        if (SecondReworderId != null) {
            updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", SecondReworderId).setSql("points = points + 50");

            userService.update(null, updateWrapper);
            boolean result1 = userService.update(null, updateWrapper);
            ThrowUtils.throwIf(!result1, ErrorCode.OPERATION_ERROR);
        }
        if (ThirdReworderId != null) {
            updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", ThirdReworderId).setSql("points = points + 30");

            userService.update(null, updateWrapper);
            boolean result1 = userService.update(null, updateWrapper);
            ThrowUtils.throwIf(!result1, ErrorCode.OPERATION_ERROR);
        }
        if (topTenReworderId.size() != 0) {

            updateWrapper = new UpdateWrapper<>();

            updateWrapper.in("id", topTenReworderId).setSql("points = points + 10");

            userService.update(null, updateWrapper);
            boolean result1 = userService.update(null, updateWrapper);
            ThrowUtils.throwIf(!result1, ErrorCode.OPERATION_ERROR);
        }

        if (otherReworderId.size() != 0) {
            updateWrapper = new UpdateWrapper<>();
            updateWrapper.in("id", otherReworderId).setSql("points = points + 5");

            userService.update(null, updateWrapper);
            boolean result1 = userService.update(null, updateWrapper);
            ThrowUtils.throwIf(!result1, ErrorCode.OPERATION_ERROR);
        }
    }


}



