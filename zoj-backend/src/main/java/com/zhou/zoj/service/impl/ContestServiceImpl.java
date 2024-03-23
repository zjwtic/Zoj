package com.zhou.zoj.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.zhou.zoj.common.ErrorCode;
import com.zhou.zoj.constant.CommonConstant;
import com.zhou.zoj.exception.BusinessException;
import com.zhou.zoj.exception.ThrowUtils;
import com.zhou.zoj.mapper.ContestMapper;
import com.zhou.zoj.model.dto.contest.ContestQueryRequest;
import com.zhou.zoj.model.entity.Contest;
import com.zhou.zoj.model.entity.Question;
import com.zhou.zoj.model.entity.QuestionSubmit;
import com.zhou.zoj.model.entity.User;
import com.zhou.zoj.model.enums.QuestionSubmitStatusEnum;
import com.zhou.zoj.model.vo.ContestVO;
import com.zhou.zoj.model.vo.UserVO;
import com.zhou.zoj.service.ContestService;
import com.zhou.zoj.service.QuestionService;
import com.zhou.zoj.service.UserService;
import com.zhou.zoj.utils.SqlUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    @Resource
    private UserService userService;

    @Resource
    private QuestionService questionService;

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
//        User loginUser = userService.getLoginUserPermitNull(request);
//        Boolean userAccepted = contestSubmitService.isUserAccepted(contestId, loginUser.getId());
//        contestVO.setIsAccepted(userAccepted);
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
//            Long contestId = contest.getId();
//            User loginUser = userService.getLoginUserPermitNull(request);
//            Boolean userAccepted = false;
//            if (loginUser != null) {
//                userAccepted = contestSubmitService.isUserAccepted(contestId, loginUser.getId());
//            }
//            contestVO.setIsAccepted(userAccepted);
            return contestVO;
        }).collect(Collectors.toList());
        contestVOPage.setRecords(contestVOList);
        return contestVOPage;
    }
}



