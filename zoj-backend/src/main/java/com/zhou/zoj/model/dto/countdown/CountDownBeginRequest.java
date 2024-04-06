package com.zhou.zoj.model.dto.countdown;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 */
@Data
public class CountDownBeginRequest implements Serializable {


    /**
     * 比赛
     */
    private Long contestId;

    /**
     * 持续时间
     */
    private Integer duration;
    private static final long serialVersionUID = 1L;
}