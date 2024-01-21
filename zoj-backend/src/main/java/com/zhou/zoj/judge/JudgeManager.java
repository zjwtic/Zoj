package com.zhou.zoj.judge;

import com.zhou.zoj.judge.strategy.DefaultJudgeStrategy;
import com.zhou.zoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.zhou.zoj.judge.strategy.JudgeContext;
import com.zhou.zoj.judge.strategy.JudgeStrategy;
import com.zhou.zoj.judge.codesandbox.model.JudgeInfo;
import com.zhou.zoj.model.entity.QuestionSubmit;
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
