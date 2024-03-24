package com.zhou.zoj.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.util.Date;

/**
 * 比赛结果(ContestResult)表实体类
 *
 * @author makejava
 * @since 2024-03-23 22:08:33
 */

@TableName(value = "contest_result")
@Data
public class ContestResult {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    //创建用户id
    private Long userId;
    //比赛id
    private Long contestId;
    //比赛做题信息（json）
    private String contestExerciseData;
    //提交状态
    private Integer submitStatus;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //是否逻辑删除
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
