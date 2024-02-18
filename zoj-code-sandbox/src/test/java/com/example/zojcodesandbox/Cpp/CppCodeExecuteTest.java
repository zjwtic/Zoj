package com.example.zojcodesandbox.Cpp;

import com.example.zojcodesandbox.CodeSandbox;
import com.example.zojcodesandbox.cpp.CppNativeCodeSandbox;
import com.example.zojcodesandbox.model.ExecuteCodeRequest;
import com.example.zojcodesandbox.model.ExecuteCodeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

public class CppCodeExecuteTest {


    @Test
    void executeCode() {
        CodeSandbox codeSandbox =new CppNativeCodeSandbox();
        String code = "        #include <iostream>\n" +
                "        #include <cstdlib>\n" +
                "\n" +
                "        int main(int argc, char **argv)\n" +
                "        {\n" +
                "\n" +
                "        \tint sum = 0;\n" +
                "        \tfor (int i = 1; i < argc; ++i)\n" +
                "        \t{\n" +
                "        \t\tint num_i = atoi(argv[i]);\n" +
                "        \t\tsum += num_i;\n" +
                "        \t}\n" +
                "        \tstd::cout << sum;\n" +
                "\n" +
                "        \treturn 0;\n" +
                "        }";

        String language = "cpp";
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