package com.zhou.zoj.model.dto.comment;



import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 35238
 * @date 2023/8/2 0002 14:40
 */
@Data
public class CommentAddRequest implements Serializable {

    private Long id;


    //文章id
    private Long postId;

    //根评论id
    private Long rootId;
    //评论内容
    private String content;
    //所回复的目标评论的userid
    private Long toCommentUserId;
    //回复目标评论id
    private Long toCommentId;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    //删除标志（0代表未删除，1代表已删除）
    private Integer isDelete;

}