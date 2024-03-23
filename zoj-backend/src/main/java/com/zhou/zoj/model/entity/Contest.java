package com.zhou.zoj.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 比赛(Contest)表实体类
 */
@Data
@TableName(value = "contest")
public class Contest {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    //创建用户id
    private Long userId;
    //选择的题目id集合
    private String selectQuestionIds;
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
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //是否逻辑删除
    @TableLogic
    private Integer isDelete;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
