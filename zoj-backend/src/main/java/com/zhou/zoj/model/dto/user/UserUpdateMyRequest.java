package com.zhou.zoj.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户更新个人信息请求
 */
@Data
public class UserUpdateMyRequest implements Serializable {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 简介
     */
    private String userProfile;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户原来密码
     */
    private String userPassword;

    /**
     * 用户确认密码
     */
    private String accessPassword;

    /**
     * 用户待更改的密码
     */
    private String updatePassword;

    private static final long serialVersionUID = 1L;
}