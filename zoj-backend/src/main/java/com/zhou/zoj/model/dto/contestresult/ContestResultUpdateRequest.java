package com.zhou.zoj.model.dto.contestresult;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 */
@Data
public class ContestResultUpdateRequest implements Serializable {


    //比赛 结果id
    private Long contestResultId;

    //比赛题目提交id
    private Long questionSubmitId;

    private static final long serialVersionUID = 1L;
}