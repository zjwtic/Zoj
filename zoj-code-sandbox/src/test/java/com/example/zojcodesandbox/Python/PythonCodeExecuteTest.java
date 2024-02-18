package com.example.zojcodesandbox.Python;

import com.example.zojcodesandbox.CodeSandbox;
import com.example.zojcodesandbox.cpp.CppNativeCodeSandbox;
import com.example.zojcodesandbox.model.ExecuteCodeRequest;
import com.example.zojcodesandbox.model.ExecuteCodeResponse;
import com.example.zojcodesandbox.python.PythonNativeCodeSandbox;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest

public class PythonCodeExecuteTest {


    @Test
    void executeCode() {
        CodeSandbox codeSandbox =new PythonNativeCodeSandbox();
        String code = "import sys\n" +
                "\n" +
                "result=0\n" +
                "for i in range(1,len(sys.argv)):\n" +
                "   result+=int(sys.argv[i])\n" +
                "print(result)\n   ";

        String language = "python";
        List<String> inputList = Arrays.asList("1 2", "3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);

        Assertions.assertNotNull(executeCodeResponse);
    }
}