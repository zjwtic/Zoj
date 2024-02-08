package com.zhou.zojbackendjudgeservice.judge.codesandbox;


import com.zhou.zojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.zhou.zojbackendmodel.model.codesandbox.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {

    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest );
}
