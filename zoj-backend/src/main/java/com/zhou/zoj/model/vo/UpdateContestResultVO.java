package com.zhou.zoj.model.vo;

import lombok.Data;


import java.io.Serializable;


/**
 * 题目提交封装类
 *
 * @TableName question
 */
@Data
public class UpdateContestResultVO implements Serializable {
    private Boolean isUpdateSuccess;
    private Boolean thisTimeIsQuestionAccepted;

    private Long thisTimeBestTimeConsume;

}