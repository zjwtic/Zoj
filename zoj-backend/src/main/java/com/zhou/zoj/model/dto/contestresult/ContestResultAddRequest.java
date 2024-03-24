package com.zhou.zoj.model.dto.contestresult;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 */
@Data
public class ContestResultAddRequest implements Serializable {


    //比赛id
    private Long contestId;


    private static final long serialVersionUID = 1L;
}