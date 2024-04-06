package com.zhou.zoj.model.dto.countdown;

import lombok.Data;

import java.io.Serializable;


/**
 * 创建请求
 */
@Data
public class CountDownGetRestTimeRequest implements Serializable {

    /**
     * 比赛
     */
    private Long contestId;

    private static final long serialVersionUID = 1L;
}