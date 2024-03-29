package com.zhou.zoj.model.dto.contest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhou.zoj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.io.Serializable;
import java.util.Date;

/**
 * 查询请求
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContestQueryRequest extends PageRequest implements Serializable {
    private Long id;

    //创建用户id
    private Long userId;
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