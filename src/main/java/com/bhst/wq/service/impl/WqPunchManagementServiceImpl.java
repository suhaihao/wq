package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqPunchManagement;
import com.bhst.wq.mapper.WqPunchManagementMapper;
import com.bhst.wq.request.WqPunchManagementDetailDelRequest;
import com.bhst.wq.request.WqPunchManagementPageListRequest;
import com.bhst.wq.service.WqPunchManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WqPunchManagementServiceImpl extends ServiceImpl<WqPunchManagementMapper, WqPunchManagement> implements WqPunchManagementService {

    private final WqPunchManagementMapper wqPunchManagementMapper;


    @Autowired
    public WqPunchManagementServiceImpl(WqPunchManagementMapper wqPunchManagementMapper) {
        this.wqPunchManagementMapper = wqPunchManagementMapper;
    }

    @Override
    public IPage<WqPunchManagement> getPageList(WqPunchManagementPageListRequest request) {
        Page<WqPunchManagement> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqPunchManagement> queryWrapperUser = new QueryWrapper();
        queryWrapperUser.orderByDesc("create_time");
        if (null != request.getActivityId()) {
            queryWrapperUser.eq("activity_id", request.getActivityId());
        }
        return wqPunchManagementMapper.selectPage(page, queryWrapperUser);
    }

    @Override
    public WqPunchManagement getById(WqPunchManagementDetailDelRequest request) {
        return wqPunchManagementMapper.selectById(request.getId());
    }

    @Override
    public Boolean delById(WqPunchManagementDetailDelRequest request) {
        return wqPunchManagementMapper.deleteById(request.getId()) > 0;
    }

    @Override
    public WqPunchManagement selectOneByTime(QueryWrapper<WqPunchManagement> queryWrapper) {
        return wqPunchManagementMapper.selectOne(queryWrapper);
    }

}
