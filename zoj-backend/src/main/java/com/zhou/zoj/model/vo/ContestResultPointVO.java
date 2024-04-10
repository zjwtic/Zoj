package com.zhou.zoj.model.vo;


import cn.hutool.json.JSONUtil;
import com.zhou.zoj.model.dto.contestresult.ContestQuestionData;

import com.zhou.zoj.model.dto.contestresult.QuestionScore;
import com.zhou.zoj.model.entity.ContestResult;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ContestResultPointVO implements Serializable {


    /**
     * 创建用户 id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;

    //比赛题目分数信息集合
    private List<QuestionScore> questionScoreList;
    //提交状态
    private Integer submitStatus;

    /**
     * 总分
     */
    private Integer point;


    private static final long serialVersionUID = 1L;


    /**
     * 包装类转对象
     *
     * @param contestResultVO
     * @return
     */
    public static ContestResultPointVO getContestResultPointVO(ContestResultVO contestResultVO) {
        if (contestResultVO == null) {
            return null;
        }
        ContestResultPointVO contestResultPointVO = new ContestResultPointVO();
        BeanUtils.copyProperties(contestResultVO, contestResultPointVO);
        if (contestResultVO.getUserVO() != null) {
            contestResultPointVO.setUserName(contestResultVO.getUserVO().getUserName());
        }
        List<ContestQuestionData> contestQuestionDatas = contestResultVO.getContestQuestionDatas();

        List<QuestionScore> questionScoreList = contestQuestionDatas.stream().map(contestQuestionData -> {
            Integer score = 0;
            if (contestQuestionData.getIsAccepted()) {
                score = 100;
            }
            Integer errorNum = contestQuestionData.getErrorNum();
            Long timeConsume = contestQuestionData.getTimeConsume();
            QuestionScore questionScore = new QuestionScore(contestQuestionData.getQuestionId(), contestQuestionData.getTitle(), score - 5 * errorNum, timeConsume);
            return questionScore;
        }).collect(Collectors.toList());
        Integer point = 0;
        for (QuestionScore questionScore : questionScoreList) {
            point += questionScore.getScore();
        }
        contestResultPointVO.setQuestionScoreList(questionScoreList);
        contestResultPointVO.setPoint(point);

        return contestResultPointVO;
    }

}