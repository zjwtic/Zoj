package com.zhou.zoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.zoj.model.dto.contest.ContestQueryRequest;
import com.zhou.zoj.model.entity.Contest;
import com.zhou.zoj.model.entity.Contest;
import com.zhou.zoj.model.vo.ContestVO;

import javax.servlet.http.HttpServletRequest;


/**
 * 比赛(Contest)表服务接口
 *
 * @author makejava
 * @since 2024-03-23 17:07:03
 */
public interface ContestService extends IService<Contest> {
    /**
     * 校验
     *
     * @param contest
     * @param add
     */
    void validContest(Contest contest, boolean add);

    /**
     * 获取查询条件
     *
     * @param contestQueryRequest
     * @return
     */
    QueryWrapper<Contest> getQueryWrapper(ContestQueryRequest contestQueryRequest);

    /**
     * 获取比赛封装
     *
     * @param contest
     * @param request
     * @return
     */
    ContestVO getContestVO(Contest contest, HttpServletRequest request);

    /**
     * 分页获取比赛封装
     *
     * @param contestPage
     * @param request
     * @return
     */
    Page<ContestVO> getContestVOPage(Page<Contest> contestPage, HttpServletRequest request);

}
