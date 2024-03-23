package com.zhou.zoj.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论表(Comment)表实体类
 */

@TableName(value = "comment")
@Data
public class Comment implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章id
     */
    private Long postId;

    /**
     * 根评论id
     */
    private Long rootId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 所回复的目标评论的userid
     */
    private Long toCommentUserId;

    /**
     * 回复目标评论id
     */
    private Long toCommentId;


    private Long createBy;


    private Date createTime;


    private Long updateBy;


    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}