package com.example.zojcodesandbox.Java;

import com.example.zojcodesandbox.CodeSandbox;
import com.example.zojcodesandbox.cpp.CppNativeCodeSandbox;
import com.example.zojcodesandbox.java.JavaNativeCodeSandbox;
import com.example.zojcodesandbox.model.ExecuteCodeRequest;
import com.example.zojcodesandbox.model.ExecuteCodeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest

public class JavaCodeExecuteTest {


    @Test
    void executeCode() {
        CodeSandbox codeSandbox =new JavaNativeCodeSandbox();
        String code = "  public class Main{\n" +
                "        public static void main(String[] args) {\n" +
                "            int result=0;\n" +
                "           for(int i=0;i<args.length;i++){\n" +
                "               result+=Integer.parseInt(args[i]);\n" +
                "           }\n" +
                "            System.out.println(result);\n" +
                "        }}   ";

        String language = "java";
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