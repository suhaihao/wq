package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqPracticeStation;
import com.bhst.wq.mapper.WqPracticeStationMapper;
import com.bhst.wq.request.WqPracticeStationDetailDelRequest;
import com.bhst.wq.request.WqPracticeStationPageListRequest;
import com.bhst.wq.service.WqPracticeStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WqPracticeStationServiceImpl extends ServiceImpl<WqPracticeStationMapper, WqPracticeStation> implements WqPracticeStationService {

    private final WqPracticeStationMapper wqPracticeStationMapper;


    @Autowired
    public WqPracticeStationServiceImpl(WqPracticeStationMapper wqPracticeStationMapper) {
        this.wqPracticeStationMapper = wqPracticeStationMapper;
    }

    @Override
    public IPage<WqPracticeStation> getPageList(WqPracticeStationPageListRequest request) {
        Page<WqPracticeStation> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqPracticeStation> queryWrapperUser = new QueryWrapper();
        if (null != request.getInstituteId()) {
            queryWrapperUser.eq("institute_id", request.getInstituteId());
        }
        queryWrapperUser.orderByDesc("create_time");
        return wqPracticeStationMapper.selectPage(page, queryWrapperUser);
    }

    @Override
    public WqPracticeStation getById(WqPracticeStationDetailDelRequest request) {
        return wqPracticeStationMapper.selectById(request.getId());
    }

    @Override
    public Boolean delById(WqPracticeStationDetailDelRequest request) {
        return wqPracticeStationMapper.deleteById(request.getId()) > 0;
    }

}
