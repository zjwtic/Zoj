package com.zhou.zoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhou.zoj.judge.codesandbox.model.JudgeInfo;
import com.zhou.zoj.model.dto.question.QuestionQueryRequest;
import com.zhou.zoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.zhou.zoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.zhou.zoj.model.entity.Question;
import com.zhou.zoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.zoj.model.entity.User;
import com.zhou.zoj.model.enums.JudgeInfoMessageEnum;
import com.zhou.zoj.model.enums.QuestionSubmitStatusEnum;
import com.zhou.zoj.model.vo.QuestionSubmitVO;
import org.apache.commons.lang3.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 32335
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2023-11-23 11:14:59
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);


    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);

    /**
     * 获取用户是否代码运行成功
     *
     * @param questionId
     * @param userId
     * @return
     */
     Boolean isUserAccepted(long questionId, long userId) ;
}
