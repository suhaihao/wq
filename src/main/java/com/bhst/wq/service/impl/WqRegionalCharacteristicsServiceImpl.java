package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqRegionalCharacteristics;
import com.bhst.wq.mapper.WqRegionalCharacteristicsMapper;
import com.bhst.wq.request.WqRegionalCharacteristicsDetailDelRequest;
import com.bhst.wq.request.WqRegionalCharacteristicsPageListRequest;
import com.bhst.wq.service.WqRegionalCharacteristicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WqRegionalCharacteristicsServiceImpl extends ServiceImpl<WqRegionalCharacteristicsMapper, WqRegionalCharacteristics> implements WqRegionalCharacteristicsService {

    private final WqRegionalCharacteristicsMapper wqRegionalCharacteristicsMapper;


    @Autowired
    public WqRegionalCharacteristicsServiceImpl(WqRegionalCharacteristicsMapper wqRegionalCharacteristicsMapper) {
        this.wqRegionalCharacteristicsMapper = wqRegionalCharacteristicsMapper;
    }

    @Override
    public IPage<WqRegionalCharacteristics> getPageList(WqRegionalCharacteristicsPageListRequest request) {
        Page<WqRegionalCharacteristics> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqRegionalCharacteristics> queryWrapperUser = new QueryWrapper();
        queryWrapperUser.orderByDesc("create_time");
        return wqRegionalCharacteristicsMapper.selectPage(page, queryWrapperUser);
    }

    @Override
    public WqRegionalCharacteristics getById(WqRegionalCharacteristicsDetailDelRequest request) {
        return wqRegionalCharacteristicsMapper.selectById(request.getId());
    }

    @Override
    public Boolean delById(WqRegionalCharacteristicsDetailDelRequest request) {
        return wqRegionalCharacteristicsMapper.deleteById(request.getId()) > 0;
    }

}
