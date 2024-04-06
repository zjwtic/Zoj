package com.zhou.zoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.zoj.common.BaseResponse;
import com.zhou.zoj.common.ErrorCode;
import com.zhou.zoj.common.ResultUtils;
import com.zhou.zoj.exception.ThrowUtils;
import com.zhou.zoj.mapper.CountDownMapper;
import com.zhou.zoj.model.dto.countdown.CountDownBeginRequest;
import com.zhou.zoj.model.dto.countdown.CountDownGetRestTimeRequest;
import com.zhou.zoj.model.entity.CountDown;
import com.zhou.zoj.model.entity.User;

import com.zhou.zoj.service.ContestResultService;
import com.zhou.zoj.service.CountDownService;
import com.zhou.zoj.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
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


    @Override
    public BaseResponse<Long> startCountdown(CountDownBeginRequest countDownBeginRequest, HttpServletRequest request) {


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
        long newPostId = countDown.getId();


//        开启定时器
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable checkCountdown = () -> {
            System.out.println("比赛倒计时定时器正在运行 userId:" + loginUser.getId() + " contestId:" + countDownBeginRequest.getContestId());
            // 在这里使用endTime变量
            //x获取当前时间和数据库中存储的倒计时结束时间
            Instant current = Instant.now();
            if (current.isAfter(future)) {
                // 倒计时结束，执行相应操作
                onCountdownFinished(loginUser.getId(), countDownBeginRequest.getContestId());
                // 停止定时任务
                scheduler.shutdown();
            }

        };
        // 设置定时任务，每隔一段时间检查一次倒计时是否结束
        scheduler.scheduleAtFixedRate(checkCountdown, 1, 1, TimeUnit.SECONDS);
        return ResultUtils.success(newPostId);
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
