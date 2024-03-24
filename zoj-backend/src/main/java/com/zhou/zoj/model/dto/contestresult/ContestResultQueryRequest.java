package com.zhou.zoj.model.dto.contestresult;

import com.zhou.zoj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 创建请求
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContestResultQueryRequest extends PageRequest implements Serializable {

    /**
     * 比赛 id
     */
    private Long contestId;

    /**
     *   用户id
     */
    private Long userId;


    private static final long serialVersionUID = 1L;
}