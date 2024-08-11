package com.zhou.zoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.zoj.common.BaseResponse;
import com.zhou.zoj.common.ErrorCode;
import com.zhou.zoj.common.ResultUtils;
import com.zhou.zoj.exception.BusinessException;
import com.zhou.zoj.exception.ThrowUtils;
import com.zhou.zoj.mapper.ContestMapper;
import com.zhou.zoj.mapper.CountDownMapper;
import com.zhou.zoj.model.dto.countdown.CountDownBeginRequest;
import com.zhou.zoj.model.dto.countdown.CountDownGetRestTimeRequest;
import com.zhou.zoj.model.entity.Contest;
import com.zhou.zoj.model.entity.CountDown;
import com.zhou.zoj.model.entity.User;

import com.zhou.zoj.service.ContestResultService;
import com.zhou.zoj.service.CountDownService;
import com.zhou.zoj.service.UserService;
import com.zhou.zoj.utils.ScheduledTaskManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import java.util.concurrent.TimeUnit;


/**
 * (CountDown)表服务实现类
 *
 * @author makejava
 * @since 2024-03-31 21:37:47
 */
@Service
public class CountDownServiceImpl extends ServiceImpl<CountDownMapper, CountDown> implements CountDownService {

    @Resource
    UserService userService;

    @Resource
    ContestResultService contestResultService;

    @Resource
    private ContestMapper contestMapper;


    private final static ScheduledTaskManager manager = new ScheduledTaskManager();


    @Override
    public BaseResponse<Long> startCountdown(CountDownBeginRequest countDownBeginRequest, HttpServletRequest request) {

        // 判断实体是否存在，根据类别获取实体
        Contest contest = contestMapper.selectById(countDownBeginRequest.getContestId());
        if (contest == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 获取当前日期
        Date date = new Date();
        // 比较时间是否满足
        if (date.before(contest.getStartTime()) || date.after(contest.getEndTime())) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR);
        }


        // 获取当前时间戳并存储到数据库
        CountDown countDown = new CountDown();
        User loginUser = userService.getLoginUser(request);
        countDown.setUserId(loginUser.getId());
        countDown.setContestId(countDownBeginRequest.getContestId());
        Instant now = Instant.now();
        countDown.setStartTimeStamp(now.toEpochMilli());
        Instant future = now.plusSeconds(countDownBeginRequest.getDuration());
        countDown.setEndTimeStamp(future.toEpochMilli());
        boolean result = save(countDown);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newCownDownId = countDown.getId();


        // 安排任务
        Runnable checkCountdown = () -> {
            // 倒计时结束，执行相应操作
            onCountdownFinished(loginUser.getId(), countDownBeginRequest.getContestId());
        };
        manager.scheduleTask(newCownDownId, Date.from(future), checkCountdown);

        return ResultUtils.success(newCownDownId);
    }


    @Override
    public BaseResponse<Long> getRemainingTime(CountDownGetRestTimeRequest countDownGetRestTimeRequest, HttpServletRequest request) {
        // 从数据库获取开始时间戳，并计算剩余时间
        User loginUser = userService.getLoginUser(request);
        QueryWrapper<CountDown> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("contestId", countDownGetRestTimeRequest.getContestId());
        queryWrapper.eq("userId", loginUser.getId());
        CountDown countDown = getOne(queryWrapper);
        if (countDown == null) {
            return ResultUtils.error(1, "倒计时不存在");
        }
        Long startTimestamp = countDown.getStartTimeStamp();
        Long endTimeStamp = countDown.getEndTimeStamp();
        Long duration = endTimeStamp - startTimestamp;
        Long now = Instant.now().toEpochMilli();
        Long elapsedTime = now - startTimestamp;
        Long remainingTime = Math.max(0, (duration - elapsedTime) / 1000);
        return ResultUtils.success(remainingTime);
    }

    private void onCountdownFinished(Long userId, Long contestId) {
        // 这里执行倒计时结束后的操作，比如发送通知、更新数据库状态
        System.out.println("比赛倒计时结束 userId:" + userId + " contestId:" + contestId);
        //自动提交
        contestResultService.contestResultSubmit(userId, contestId);
    }
}
