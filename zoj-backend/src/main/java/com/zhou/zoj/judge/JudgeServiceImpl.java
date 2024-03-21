package com.zhou.zoj.judge;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.zoj.common.ErrorCode;
import com.zhou.zoj.exception.BusinessException;
import com.zhou.zoj.judge.codesandbox.CodeSandbox;
import com.zhou.zoj.judge.codesandbox.CodeSandboxFactory;
import com.zhou.zoj.judge.codesandbox.CodeSandboxProxy;
import com.zhou.zoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zhou.zoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.zhou.zoj.judge.strategy.JudgeContext;
import com.zhou.zoj.model.dto.question.JudgeCase;
import com.zhou.zoj.judge.codesandbox.model.JudgeInfo;
import com.zhou.zoj.model.entity.PostThumb;
import com.zhou.zoj.model.entity.Question;
import com.zhou.zoj.model.entity.QuestionSubmit;
import com.zhou.zoj.model.entity.User;
import com.zhou.zoj.model.enums.JudgeInfoMessageEnum;
import com.zhou.zoj.model.enums.QuestionSubmitStatusEnum;
import com.zhou.zoj.service.PostThumbService;
import com.zhou.zoj.service.QuestionService;
import com.zhou.zoj.service.QuestionSubmitService;
import com.zhou.zoj.service.UserService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${codesandbox.type:example}")
    private String type;


    @Resource
    private QuestionService questionService;

    @Resource
    private UserService userService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private JudgeManager judgeManager;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {

        // 1）传入题目的提交 id，获取到对应的题目、提交信息（包含代码、编程语言等）
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }

        // 2）如果题目提交状态不为等待中，就不用重复执行了
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }

        // 3）更改判题（题目提交）的状态为 “判题中”，防止重复执行
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }

        //4）调用沙箱,获取到执行结果
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        //获取输入用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
        // 5）根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        // 6）修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        if (executeCodeResponse.getStatus().equals(2)) {
            questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        } else if (executeCodeResponse.getStatus().equals(3)) {
            questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.FAILED.getValue());
        }
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));

//        更新前先拿到现有是否有成功过
        Long userId = questionSubmit.getUserId();
        Boolean userAccepted = questionSubmitService.isUserAccepted(questionId, userId);

        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }

        Boolean isSuccess = judgeInfo.getMessage().equals(JudgeInfoMessageEnum.ACCEPTED.getValue());
        //        进行提交数量 和 通过数量 更新
        synchronized (String.valueOf(questionId).intern()) {
            doQuestionAcceptedAndSubmitNumInner(questionId, userId, isSuccess, userAccepted);
        }


        QuestionSubmit questionSubmitResult = questionSubmitService.getById(questionSubmitId);
        return questionSubmitResult;

    }

    /**
     * 重置 提交和成功数   规则： 同一个用户成功 1次 算1次   成功多次也算1次   提交数 即 只要提交了就算1次
     *
     * @param questionId   当前questionid
     * @param userId   当前userId
     * @param isSuccess    这次是否成功
     * @param userAccepted 曾经是否成功过
     * @return
     */
    @Transactional
    public int doQuestionAcceptedAndSubmitNumInner(long questionId, long userId, boolean isSuccess, boolean userAccepted) {
        boolean result;
        if (!isSuccess || userAccepted) {
            result = questionService.update()
                    .eq("id", questionId)
                    .setSql("submitNum = submitNum + 1")
                    .update();
            if (result) {
                return result ? -1 : 0;
            } else {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        } else {
            result = questionService.update()
                    .eq("id", questionId)
                    .setSql("submitNum = submitNum + 1, acceptedNum = acceptedNum + 1")
                    .update();

            if (result) {
                result = userService.update()
                        .eq("id", userId)
                        .setSql("acceptedNum = acceptedNum + 1")
                        .update();
                if (result) {
                    return result ? -1 : 0;
                } else {
                    throw new BusinessException(ErrorCode.SYSTEM_ERROR);
                }
            } else {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        }
    }


}