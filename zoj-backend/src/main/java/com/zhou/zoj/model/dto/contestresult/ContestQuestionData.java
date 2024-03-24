package com.zhou.zoj.model.dto.contestresult;

import lombok.Data;

@Data
public class ContestQuestionData {
    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 题目名字
     */
    private String title;

    /**
     * 题目是否完成
     */
    private Boolean isAccepted;

    /**
     * 题目运行错误次数
     */
    private Integer errorNum;

    /**
     * 时间消耗
     */
    private Long timeConsume;

    private ContestQuestionData() {
    }

    public ContestQuestionData(Long questionId, String title) {
        this.questionId = questionId;
        this.title = title;
        this.errorNum = 0;
        this.timeConsume = 0L;
        this.isAccepted = false;


    }
}
