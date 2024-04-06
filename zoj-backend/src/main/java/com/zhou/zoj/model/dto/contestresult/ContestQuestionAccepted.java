package com.zhou.zoj.model.dto.contestresult;

import lombok.Data;

@Data
public class ContestQuestionAccepted {
    /**
     * 题目id
     */
    private Long questionId;


    /**
     * 题目运行成功数量
     */
    private Integer questionAcceptedNum;


    /**
     * 题目标题
     */
    private String title;

    private ContestQuestionAccepted() {
    }

    public ContestQuestionAccepted(Long questionId, Integer questionAcceptedNum, String title) {
        this.questionId = questionId;
        this.title = title;
        this.questionAcceptedNum = questionAcceptedNum;

    }
}
