package com.zhou.zoj.model.dto.contest;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 创建请求
 */
@Data
public class ContestAddRequest implements Serializable {
    //选择的题目id集合
    private List<String> selectQuestionIds;
    //比赛名称
    private String contestName;
    //比赛描述
    private String contestDescription;
    //比赛开始时间
    private Date startTime;
    //比赛结束时间
    private Date endTime;
    //比赛时间
    private Integer duration;

    private static final long serialVersionUID = 1L;

}