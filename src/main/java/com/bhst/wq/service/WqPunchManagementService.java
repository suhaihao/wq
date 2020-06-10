package com.bhst.wq.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhst.wq.entity.WqActivityRecruitment;
import com.bhst.wq.entity.WqPunchManagement;
import com.bhst.wq.entity.WqUser;
import com.bhst.wq.request.WqPunchManagementDetailDelRequest;
import com.bhst.wq.request.WqPunchManagementPageListRequest;

import java.util.List;

public interface WqPunchManagementService extends IService<WqPunchManagement> {


    /**
     * 分页获取列表信息
     *
     * @param request
     * @return
     */
    IPage<WqPunchManagement> getPageList(WqPunchManagementPageListRequest request);

    /**
     * 分页获取用户参加活动列表信息
     *
     * @param request
     * @return
     */
    IPage<WqUser> getUserPageList(WqPunchManagementPageListRequest request);

    /**
     * @param request
     * @return
     */
    WqPunchManagement getById(WqPunchManagementDetailDelRequest request);

    /**
     * @param request
     * @return
     */
    Boolean delById(WqPunchManagementDetailDelRequest request);

    /**
     * 根据条件获取唯一
     * @param queryWrapper
     * @return
     */
    WqPunchManagement selectOneByTime(QueryWrapper<WqPunchManagement> queryWrapper);

    /**
     * 查询用户参加过的活动列表
     * @param request
     * @return
     */
    List<WqActivityRecruitment> getByUserActivity(WqPunchManagementPageListRequest request);
}
