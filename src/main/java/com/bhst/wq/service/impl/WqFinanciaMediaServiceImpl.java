package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqFinancialMedia;
import com.bhst.wq.mapper.WqFinanciaMediaMapper;
import com.bhst.wq.request.WqFinanciaMediaDetailDelRequest;
import com.bhst.wq.request.WqFinanciaMediaPageListRequest;
import com.bhst.wq.service.WqFinanciaMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WqFinanciaMediaServiceImpl extends ServiceImpl<WqFinanciaMediaMapper, WqFinancialMedia> implements WqFinanciaMediaService {

    private final WqFinanciaMediaMapper wqFinanciaMediaMapper;


    @Autowired
    public WqFinanciaMediaServiceImpl(WqFinanciaMediaMapper wqFinanciaMediaMapper) {
        this.wqFinanciaMediaMapper = wqFinanciaMediaMapper;
    }

    @Override
    public IPage<WqFinancialMedia> getPageList(WqFinanciaMediaPageListRequest request) {
        Page<WqFinancialMedia> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqFinancialMedia> queryWrapperUser = new QueryWrapper();
        queryWrapperUser.orderByDesc("create_time");
        return wqFinanciaMediaMapper.selectPage(page, queryWrapperUser);
    }

    @Override
    public WqFinancialMedia getById(WqFinanciaMediaDetailDelRequest request) {
        return wqFinanciaMediaMapper.selectById(request.getId());
    }

    @Override
    public Boolean delById(WqFinanciaMediaDetailDelRequest request) {
        return wqFinanciaMediaMapper.deleteById(request.getId()) > 0;
    }

}
