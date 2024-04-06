package com.zhou.zoj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.zoj.common.BaseResponse;
import com.zhou.zoj.model.dto.countdown.CountDownBeginRequest;
import com.zhou.zoj.model.dto.countdown.CountDownGetRestTimeRequest;
import com.zhou.zoj.model.entity.CountDown;

import javax.servlet.http.HttpServletRequest;


/**
 * (CountDown)表服务接口
 *
 * @author makejava
 * @since 2024-03-31 21:37:46
 */
public interface CountDownService extends IService<CountDown> {

    BaseResponse<Long> startCountdown(CountDownBeginRequest countDownBeginRequest, HttpServletRequest request);

    BaseResponse<Long> getRemainingTime(CountDownGetRestTimeRequest countDownGetRestTimeRequest, HttpServletRequest request);
}
