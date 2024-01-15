package com.zhou.zoj.judge.codesandbox;


import com.zhou.zoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.zhou.zoj.judge.codesandbox.model.ExecuteCodeRequest;

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
