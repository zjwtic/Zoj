package com.zhou.zojbackendjudgeservice.judge.codesandbox.impl;


import com.zhou.zojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.zhou.zojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.zhou.zojbackendmodel.model.codesandbox.ExecuteCodeResponse;

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
