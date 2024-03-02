package com.zhou.zoj.model.dto.comment;

import com.zhou.zoj.common.PageRequest;
import com.zhou.zoj.model.dto.post.PostQueryRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 帖子收藏查询请求
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommentQueryRequest extends PageRequest implements Serializable {


    /**
     * 用户 id
     */
    private Long postId;

    private static final long serialVersionUID = 1L;
}