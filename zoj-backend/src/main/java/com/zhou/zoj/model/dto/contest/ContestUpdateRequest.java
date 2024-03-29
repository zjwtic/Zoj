package com.zhou.zoj.model.dto.contest;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 更新请求
 */
@Data
public class ContestUpdateRequest implements Serializable {
    private Long id;

    //选择的题目id集合
    private List<String> selectQuestionIds;
    //比赛名称
    private String contestName;
    //比赛描述
    private String contestDescription;
    //比赛开始时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    //比赛结束时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    //比赛时间
    private Integer duration;


    private static final long serialVersionUID = 1L;
}