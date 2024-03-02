package com.zhou.zoj.model.vo;

import cn.hutool.json.JSONUtil;
import com.zhou.zoj.model.dto.question.JudgeConfig;
import com.zhou.zoj.model.entity.Comment;
import com.zhou.zoj.model.entity.Question;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 35238
 * @date 2023/7/25 0025 13:49
 */
@Data
@Accessors(chain = true)
public class CommentVO implements Serializable {

    private Long id;

    //文章id
    private Long postId;
    //根评论id
    private Long rootId;
    //评论内容
    private String content;
    //发根评论的userid
    private Long toCommentUserId;
    //发根评论的userName
    private String toCommentUserName;
    //回复目标评论id
    private Long toCommentId;
    //当前评论的创建人id
    private Long createBy;

    private Date createTime;

    //评论是谁发的
    private String username;

    //评论人的头像
    private String userAvatar;

    //子评论
    private List<CommentVO> children;
    private static final long serialVersionUID = 1L;

    /**
     * 包装类转对象
     *
     * @param commentVO
     * @return
     */
    public static Comment voToObj(CommentVO commentVO) {
        if (commentVO == null) {
            return null;
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentVO, comment);

        return comment;
    }

    /**
     * 对象转包装类
     *
     * @param comment
     * @return
     */
    public static CommentVO objToVo(Comment comment) {
        if (comment == null) {
            return null;
        }
        CommentVO commentVO = new CommentVO();
        BeanUtils.copyProperties(comment, commentVO);
        return commentVO;
    }
}
