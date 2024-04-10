package com.zhou.zoj.model.dto.contestresult;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionScore implements Serializable {
    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 题目名字
     */
    private String title;

    /**
     * 题目分数
     */
    private Integer score;


    /**
     * 题目用时
     */
    private Long consume;

    private QuestionScore() {
    }

    public QuestionScore(Long questionId, String title, Integer score, Long consume) {
        this.questionId = questionId;
        this.title = title;
        this.score = Math.max(0, score);
        this.consume = consume;
    }
}
