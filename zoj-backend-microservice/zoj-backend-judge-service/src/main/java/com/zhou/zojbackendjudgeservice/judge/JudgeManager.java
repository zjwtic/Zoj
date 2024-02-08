package com.zhou.zojbackendjudgeservice.judge;


import com.zhou.zojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.zhou.zojbackendjudgeservice.judge.strategy.JavaLanguageJudgeStrategy;
import com.zhou.zojbackendjudgeservice.judge.strategy.JudgeContext;
import com.zhou.zojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.zhou.zojbackendmodel.model.codesandbox.JudgeInfo;
import com.zhou.zojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
