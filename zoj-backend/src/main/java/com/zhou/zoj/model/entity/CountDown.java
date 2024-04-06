package com.zhou.zoj.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;


import java.util.Date;

/**
 * (CountDown)表实体类
 *
 * @author makejava
 * @since 2024-03-31 21:37:46
 */


@TableName(value = "count_down")
@Data
public class CountDown {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    //用户id
    private Long userId;
    //比赛id
    private Long contestId;
    //开始时间戳
    private Long startTimeStamp;
    //结束时间戳
    private Long endTimeStamp;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
