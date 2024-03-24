package com.zhou.zoj.model.vo;

import cn.hutool.json.JSONUtil;
import com.zhou.zoj.judge.codesandbox.model.JudgeInfo;
import com.zhou.zoj.model.dto.contestresult.ContestQuestionData;
import com.zhou.zoj.model.entity.ContestResult;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目提交封装类
 *
 * @TableName question
 */
@Data
public class ContestResultVO implements Serializable {

    /**
     * id
     */
    private Long id;

    //比赛题目信息集合
    private List<ContestQuestionData> ContestQuestionDatas;
    //提交状态
    private Integer submitStatus;

    /**
     * 比赛 id
     */
    private Long contestId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 对应用户信息
     */
    private UserVO userVO;

    /**
     * 对应题目信息
     */
    private ContestVO contestVO;


    private static final long serialVersionUID = 1L;


    /**
     * 包装类转对象
     *
     * @param contestResultVO
     * @return
     */
    public static ContestResult voToObj(ContestResultVO contestResultVO) {
        if (contestResultVO == null) {
            return null;
        }
        ContestResult contestResult = new ContestResult();
        BeanUtils.copyProperties(contestResultVO, contestResult);
        List<ContestQuestionData> contestQuestionDatalist = contestResultVO.getContestQuestionDatas();
        if (contestQuestionDatalist != null) {
            contestResult.setContestExerciseData(JSONUtil.toJsonStr(contestQuestionDatalist));
        }

        return contestResult;
    }

    /**
     * 对象转包装类
     *
     * @param contestResult
     * @return
     */
    public static ContestResultVO objToVo(ContestResult contestResult) {
        if (contestResult == null) {
            return null;
        }
        ContestResultVO contestResultVO = new ContestResultVO();
        BeanUtils.copyProperties(contestResult, contestResultVO);
        List<ContestQuestionData> contestQuestionDatalist = JSONUtil.toList(contestResult.getContestExerciseData(), ContestQuestionData.class);
        contestResultVO.setContestQuestionDatas(contestQuestionDatalist);
        return contestResultVO;
    }
}