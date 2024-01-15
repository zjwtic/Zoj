package com.zhou.zoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.zoj.common.ErrorCode;
import com.zhou.zoj.constant.CommonConstant;
import com.zhou.zoj.exception.BusinessException;
import com.zhou.zoj.judge.JudgeService;
import com.zhou.zoj.model.dto.question.QuestionQueryRequest;
import com.zhou.zoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.zhou.zoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.zhou.zoj.model.entity.Question;
import com.zhou.zoj.model.entity.QuestionSubmit;
import com.zhou.zoj.model.entity.User;
import com.zhou.zoj.model.enums.QuestionSubmitLanguageEnum;
import com.zhou.zoj.model.enums.QuestionSubmitStatusEnum;
import com.zhou.zoj.model.vo.QuestionSubmitVO;
import com.zhou.zoj.model.vo.QuestionVO;
import com.zhou.zoj.model.vo.UserVO;
import com.zhou.zoj.service.QuestionService;
import com.zhou.zoj.service.QuestionSubmitService;
import com.zhou.zoj.mapper.QuestionSubmitMapper;
import com.zhou.zoj.service.UserService;
import com.zhou.zoj.utils.SqlUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author 32335
 * @description 针对表【question_submit(题目提交)】的数据库操作Service实现
 * @createDate 2023-11-23 11:14:59
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {
    @Resource
    private QuestionService questionService;

    @Resource
    private UserService userService;


    @Resource
    @Lazy
    private JudgeService judgeService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        // 效验编程语言是否合法
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (languageEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言错误");
        }
        long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已提交题目
        long userId = loginUser.getId();
        // 每个用户串行提交题目
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setLanguage(questionSubmitAddRequest.getLanguage());
        //  设置初始状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");
        boolean save = this.save(questionSubmit);
        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据插入失败");
        }
        Long questionSubmitId = questionSubmit.getId();
        //执行判题服务
        CompletableFuture.runAsync(() -> judgeService.doJudge(questionSubmitId));
        return questionSubmitId;
    }

    /**
     * 获取查询包装类
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest) {
        QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
        if (questionSubmitQueryRequest == null) {
            return queryWrapper;
        }
        String language = questionSubmitQueryRequest.getLanguage();
        Integer status = questionSubmitQueryRequest.getStatus();
        Long questionId = questionSubmitQueryRequest.getQuestionId();
        Long userId = questionSubmitQueryRequest.getUserId();
        String sortField = questionSubmitQueryRequest.getSortField();
        String sortOrder = questionSubmitQueryRequest.getSortOrder();


        // 拼接查询条件
        queryWrapper.eq(StringUtils.isNotBlank(language), "language", language);
        queryWrapper.eq(ObjectUtils.isNotEmpty(questionId), "questionId", questionId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq(QuestionSubmitStatusEnum.getEnumByValue(status) != null, "status", status);
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser) {
        QuestionSubmitVO questionSubmitVO = QuestionSubmitVO.objToVo(questionSubmit);
        //脱敏：仅本人和管理员能看见自己（提交userid和登录用户id不同）提交的代码
        long userId = loginUser.getId();//登录id
        //处理脱敏
        if (userId != questionSubmit.getUserId() && !userService.isAdmin(loginUser)) {
            questionSubmitVO.setCode(null);
        }

//        long questionSubmitId = questionSubmit.getId();
//        // 1. 关联查询用户信息
//        Long userId = questionSubmit.getUserId();
//        Long questionId=questionSubmit.getQuestionId();
//        User user = null;
//        Question question=null;
//        if (userId != null && userId > 0) {
//            user = userService.getById(userId);
//        }
//
//        UserVO userVO = userService.getUserVO(user);

//        if (questionId != null && questionId > 0) {
//            question = questionService.getById(questionId);
//        }
//
//        QuestionVO questionVO= questionService.getQuestionVO(question,request);
//        questionSubmitVO.setQuestionVO(questionVO);
//        // 2. 已登录，获取用户点赞、收藏状态
//        User loginUser = userService.getLoginUserPermitNull(request);
//        if (loginUser != null) {
//            // 获取点赞
//            QueryWrapper<QuestionSubmitThumb> questionSubmitThumbQueryWrapper = new QueryWrapper<>();
//            questionSubmitThumbQueryWrapper.in("questionSubmitId", questionSubmitId);
//            questionSubmitThumbQueryWrapper.eq("userId", loginUser.getId());
//            QuestionSubmitThumb questionSubmitThumb = questionSubmitThumbMapper.selectOne(questionSubmitThumbQueryWrapper);
//            questionSubmitVO.setHasThumb(questionSubmitThumb != null);
//            // 获取收藏
//            QueryWrapper<QuestionSubmitFavour> questionSubmitFavourQueryWrapper = new QueryWrapper<>();
//            questionSubmitFavourQueryWrapper.in("questionSubmitId", questionSubmitId);
//            questionSubmitFavourQueryWrapper.eq("userId", loginUser.getId());
//            QuestionSubmitFavour questionSubmitFavour = questionSubmitFavourMapper.selectOne(questionSubmitFavourQueryWrapper);
//            questionSubmitVO.setHasFavour(questionSubmitFavour != null);
//        }
        return questionSubmitVO;
    }

    @Override
    public Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser) {
        List<QuestionSubmit> questionSubmitList = questionSubmitPage.getRecords();
        Page<QuestionSubmitVO> questionSubmitVOPage = new Page<>(questionSubmitPage.getCurrent(), questionSubmitPage.getSize(),
                questionSubmitPage.getTotal());
        if (CollectionUtils.isEmpty(questionSubmitList)) {
            return questionSubmitVOPage;
        }
        List<QuestionSubmitVO> questionSubmitVOList = questionSubmitList.stream()
                .map(questionSubmit -> getQuestionSubmitVO(questionSubmit, loginUser))
                .collect(Collectors.toList());
        questionSubmitVOPage.setRecords(questionSubmitVOList);
        return questionSubmitVOPage;
    }


}




