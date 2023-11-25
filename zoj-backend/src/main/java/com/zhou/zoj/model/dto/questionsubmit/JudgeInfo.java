package com.zhou.zoj.model.dto.questionsubmit;

import lombok.Data;

@Data
public class JudgeInfo {
    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 消耗时间（ms）
     */
    private Long time;

    /**
     * 消耗空间（Kb）
     */
    private Long memory;
}
