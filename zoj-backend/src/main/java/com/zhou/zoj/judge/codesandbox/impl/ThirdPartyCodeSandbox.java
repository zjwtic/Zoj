package com.zhou.zoj.judge.codesandbox.impl;

import com.zhou.zoj.judge.codesandbox.CodeSandbox;
import com.zhou.zoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.zhou.zoj.judge.codesandbox.model.ExecuteCodeRequest;

/**
 * 第三方代码沙箱（实际网上现成的沙箱 ）
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeResquest) {
        System.out.println("第三方沙箱");
        return null;
    }
}
