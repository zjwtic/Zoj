package com.zhou.zoj.model.dto.contestresult;

import com.zhou.zoj.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 *
 */
@Data
public class ContestResultSubmitRequest extends PageRequest implements Serializable {

    /**
     * 比赛 id
     */
    private Long contestId;


    private static final long serialVersionUID = 1L;
}