package com.example.zojcodesandbox;


import com.example.zojcodesandbox.model.ExecuteCodeRequest;
import com.example.zojcodesandbox.model.ExecuteCodeResponse;
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
