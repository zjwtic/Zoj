package com.zhou.zoj.controller;


import com.zhou.zoj.common.BaseResponse;

import com.zhou.zoj.model.dto.countdown.CountDownBeginRequest;
import com.zhou.zoj.model.dto.countdown.CountDownGetRestTimeRequest;
import com.zhou.zoj.service.CountDownService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 倒计时表
 */
@RestController
@RequestMapping("/countdown")
@Slf4j
public class CountdownController {

    @Resource
    private CountDownService countdownService;

    @PostMapping("/start")
    public BaseResponse<Long> startCountdown(@RequestBody CountDownBeginRequest countDownBeginRequest, HttpServletRequest request) {
        // 开始倒计时，存储开始时间戳到数据库
        return countdownService.startCountdown(countDownBeginRequest, request);
    }

    @PostMapping("/remaining_time")
    public BaseResponse<Long> getRemainingTime(@RequestBody CountDownGetRestTimeRequest countDownGetRestTimeRequest, HttpServletRequest request) {
        // 获取剩余时间
        return countdownService.getRemainingTime(countDownGetRestTimeRequest, request);
    }
}

