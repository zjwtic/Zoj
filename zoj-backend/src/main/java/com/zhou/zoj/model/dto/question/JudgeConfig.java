package com.zhou.zoj.model.dto.question;

import lombok.Data;

@Data
public class JudgeConfig {
    /**
     * 时间限制（ms）
     */
    private Long timeLimit;

    /**
     * 内存限制（ks）
     */
    private Long memoryLimit;

    /**
     * 堆栈限制（KB）
     */
    private Long stackLimit;
}
