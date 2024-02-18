package com.example.zojcodesandbox.cpp;

import cn.hutool.core.io.FileUtil;
import com.example.zojcodesandbox.java.JavaCodeSandboxTemplate;
import com.example.zojcodesandbox.model.ExecuteMessage;
import com.example.zojcodesandbox.utils.ProcessUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CppNativeCodeSandbox extends JavaCodeSandboxTemplate {

    private static final String GLOBAL_CODE_DIR_NAME = "tmpCode";

    private static final String GLOBAL_CLASS_NAME = "Main.cpp";
    private static final String GLOBAL_CPP_EXECUTE_NAME = "Main.exe";

    private static final long TIME_OUT = 5000L;

    /**
     * 1. 把用户的代码保存为文件
     *
     * @param code 用户代码
     * @return
     */
    @Override
    public File saveCodeToFile(String code) {
        String userDir = System.getProperty("user.dir");
        String globalCodePathName = userDir + File.separator + GLOBAL_CODE_DIR_NAME;
        // 判断全局代码目录是否存在，没有则新建
        if (!FileUtil.exist(globalCodePathName)) {
            FileUtil.mkdir(globalCodePathName);
        }

        // 把用户的代码隔离存放
        String userCodeParentPath = globalCodePathName + File.separator + UUID.randomUUID();
        String userCodePath = userCodeParentPath + File.separator + GLOBAL_CLASS_NAME;
        File userCodeFile = FileUtil.writeString(code, userCodePath, StandardCharsets.UTF_8);
        return userCodeFile;
    }

    /**
     * 2、编译代码
     *
     * @param userCodeFile
     * @return
     */
    @Override
    public ExecuteMessage compileFile(File userCodeFile) {
        String usercodecompilepath = userCodeFile.getAbsolutePath();
        String usercodeexecutepath = usercodecompilepath.replaceFirst(GLOBAL_CLASS_NAME, GLOBAL_CPP_EXECUTE_NAME);
        String compileCmd = String.format("g++ -o %s -fexec-charset=GBK  -Wl,--stack=268435456 %s", usercodeexecutepath, usercodecompilepath);
//        g++ -o D:/桌面/hello.exe -Wl,--stack=268435456 -fexec-charset=GBK D:/桌面/hello.cpp
//        -Wl,--stack=268435456 :  限制大小256m
//        -fexec-charset=GBK：设置中文编码

        try {
            Process compileProcess = Runtime.getRuntime().exec(compileCmd);
            ExecuteMessage executeMessage = ProcessUtils.runProcessAndGetMessage(compileProcess, "编译");
            return executeMessage;
        } catch (Exception e) {
//            return getErrorResponse(e);
            throw new RuntimeException("编译错误",e);
        }
    }

    /**
     * 3、执行文件，获得执行结果列表
     *
     * @param userCodeFile
     * @param inputList
     * @return
     */
    @Override
    public List<ExecuteMessage> runFile(File userCodeFile, List<String> inputList) {
        String userCodeParentPath = userCodeFile.getAbsolutePath();
        String usercodeexecutepath = userCodeParentPath.replaceFirst(GLOBAL_CLASS_NAME, GLOBAL_CPP_EXECUTE_NAME);

        List<ExecuteMessage> executeMessageList = new ArrayList<>();
        for (String inputArgs : inputList) {
            String runCmd = String.format(" %s  %s", usercodeexecutepath, inputArgs);
            try {
                Process runProcess = Runtime.getRuntime().exec(runCmd);
                // 超时控制
                new Thread(() -> {
                    try {
                        Thread.sleep(TIME_OUT);
                        System.out.println("超时了，中断");
                        runProcess.destroy();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
                ExecuteMessage executeMessage = ProcessUtils.runProcessAndGetMessage(runProcess, "运行");
                System.out.println(executeMessage);
                executeMessageList.add(executeMessage);
            } catch (Exception e) {
                throw new RuntimeException("执行错误", e);
            }
        }
        return executeMessageList;
    }


}
