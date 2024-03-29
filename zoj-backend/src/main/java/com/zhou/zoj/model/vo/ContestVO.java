package com.zhou.zoj.model.vo;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhou.zoj.model.entity.Contest;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目封装类
 *
 * @TableName contest
 */
@Data
public class ContestVO implements Serializable {
    private Long id;

    //创建用户id
    private Long userId;
    //选择的题目id集合
    private List<String> selectContestIds;
    //比赛名称
    private String contestName;
    //比赛描述
    private String contestDescription;
    //比赛开始时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 到后台，例如入参报文到后台
    private Date startTime;
    //比赛结束时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 到后台，例如入参报文到后台
    private Date endTime;
    //比赛时间
    private Integer duration;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    /**
     * 创建题目人的信息
     */
    private UserVO userVO;

    private static final long serialVersionUID = 1L;


    /**
     * 包装类转对象
     *
     * @param contestVO
     * @return
     */
    public static Contest voToObj(ContestVO contestVO) {
        if (contestVO == null) {
            return null;
        }
        Contest contest = new Contest();
        BeanUtils.copyProperties(contestVO, contest);
        List<String> selectContestIds = contestVO.getSelectContestIds();
        if (selectContestIds != null) {
            contest.setSelectQuestionIds(JSONUtil.toJsonStr(selectContestIds));
        }
        return contest;
    }

    /**
     * 对象转包装类
     *
     * @param contest
     * @return
     */
    public static ContestVO objToVo(Contest contest) {
        if (contest == null) {
            return null;
        }
        ContestVO contestVO = new ContestVO();
        BeanUtils.copyProperties(contest, contestVO);
        List<String> setlectQuestionIdlist = JSONUtil.toList(contest.getSelectQuestionIds(), String.class);
        contestVO.setSelectContestIds(setlectQuestionIdlist);
        return contestVO;
    }
}