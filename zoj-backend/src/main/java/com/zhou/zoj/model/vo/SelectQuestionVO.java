package com.zhou.zoj.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 管理员选择题目视图
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectQuestionVO {
    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;


}
