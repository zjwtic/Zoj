package com.example.zojcodesandbox.controller;

import com.example.zojcodesandbox.cpp.CppNativeCodeSandbox;
import com.example.zojcodesandbox.java.JavaNativeCodeSandbox;
import com.example.zojcodesandbox.model.ExecuteCodeRequest;
import com.example.zojcodesandbox.model.ExecuteCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.zojcodesandbox.python.PythonNativeCodeSandbox;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MainController {
    // 定义鉴权请求头和密钥
    private static final String AUTH_REQUEST_HEADER = "auth";

    private static final String AUTH_REQUEST_SECRET = "secretKey";

    @Resource
    private JavaNativeCodeSandbox javaNativeCodeSandbox;

    @Resource
    private PythonNativeCodeSandbox pythonNativeCodeSandbox;

    @Resource
    private CppNativeCodeSandbox cppNativeCodeSandbox;

    @GetMapping("/health")
    public String healthcheck(){
        return "ok";
    }

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    @PostMapping("/executeCode")
    ExecuteCodeResponse executeCode(@RequestBody ExecuteCodeRequest executeCodeRequest, HttpServletRequest request,
                                    HttpServletResponse response) {
        // 基本的认证
        String authHeader = request.getHeader(AUTH_REQUEST_HEADER);
        if (!AUTH_REQUEST_SECRET.equals(authHeader)) {
            response.setStatus(403);
            return null;
        }
        if (executeCodeRequest == null) {
            throw new RuntimeException("请求参数为空");
        }
        String language = executeCodeRequest.getLanguage();
        if ("cpp".equals(language)){
            return cppNativeCodeSandbox.executeCode(executeCodeRequest);
        }
        if ("python".equals(language)){
            return pythonNativeCodeSandbox.executeCode(executeCodeRequest);
        }
        //默认都是java
        return javaNativeCodeSandbox.executeCode(executeCodeRequest);
    }


}
