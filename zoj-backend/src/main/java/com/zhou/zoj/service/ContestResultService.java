package com.zhou.zoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.zoj.model.dto.contestresult.ContestResultAddRequest;
import com.zhou.zoj.model.dto.contestresult.ContestResultQueryRequest;
import com.zhou.zoj.model.entity.ContestResult;
import com.zhou.zoj.model.entity.User;
import com.zhou.zoj.model.vo.ContestResultVO;

import java.util.List;


/**
 * 比赛结果(ContestResult)表服务接口
 *
 * @author makejava
 * @since 2024-03-23 22:08:34
 */
public interface ContestResultService extends IService<ContestResult> {
    /**
     * 比赛结果
     *
     * @param contestResultAddRequest 比赛结果信息
     * @param loginUser
     * @return
     */
    long addContestResult(ContestResultAddRequest contestResultAddRequest, User loginUser);


    /**
     * 获取查询条件
     *
     * @param contestResultQueryRequest
     * @return
     */
    QueryWrapper<ContestResult> getQueryWrapper(ContestResultQueryRequest contestResultQueryRequest);

    /**
     * 获取比赛结果封装
     *
     * @param contestResult
     * @param loginUser
     * @return
     */
    ContestResultVO getContestResultVO(ContestResult contestResult, User loginUser);

    /**
     * 分页获取比赛结果封装
     *
     * @param contestResultPage
     * @param loginUser
     * @return
     */
    Page<ContestResultVO> getContestResultVOPage(Page<ContestResult> contestResultPage, User loginUser);

    /**
     * 获取用户是否代码运行成功
     *
     * @param contestId
     * @param userId
     * @return
     */
    Boolean isUserSubmitted(long contestId, long userId) ;

    Boolean contestResultSubmit(Long id, Long contestId);

    List<ContestResultVO> listAllContestResultVO(long contestId);

}
