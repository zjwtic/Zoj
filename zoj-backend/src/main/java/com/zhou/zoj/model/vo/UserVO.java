package com.zhou.zoj.model.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 用户视图（脱敏）
 */
@Data
public class UserVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;


    /**
     * 用户通过题目数
     */
    private Integer acceptedNum;

    /**
     * 用户比赛积分数
     */
    private Integer points;
    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}