package com.zhou.zoj.judge.codesandbox.impl;

import com.zhou.zoj.judge.codesandbox.CodeSandbox;
import com.zhou.zoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.zhou.zoj.judge.codesandbox.model.ExecuteCodeRequest;

/**
 * 远程代码沙箱（实际调用接口的沙箱 ）
 */
public class RemoteCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeResquest) {
        System.out.println("远程沙箱");
        return null;
    }
}
