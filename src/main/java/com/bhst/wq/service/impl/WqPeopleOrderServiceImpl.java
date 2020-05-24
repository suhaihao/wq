package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqPeopleOrder;
import com.bhst.wq.mapper.WqPeopleOrderMapper;
import com.bhst.wq.request.WqPeopleOrderDetailDelRequest;
import com.bhst.wq.request.WqPeopleOrderPageListRequest;
import com.bhst.wq.service.WqPeopleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WqPeopleOrderServiceImpl extends ServiceImpl<WqPeopleOrderMapper, WqPeopleOrder> implements WqPeopleOrderService {

    private final WqPeopleOrderMapper wqPeopleOrderMapper;


    @Autowired
    public WqPeopleOrderServiceImpl(WqPeopleOrderMapper wqPeopleOrderMapper) {
        this.wqPeopleOrderMapper = wqPeopleOrderMapper;
    }

    @Override
    public IPage<WqPeopleOrder> getPageList(WqPeopleOrderPageListRequest request) {
        Page<WqPeopleOrder> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqPeopleOrder> queryWrapperUser = new QueryWrapper();
        if (null != request.getType()) {
            queryWrapperUser.eq("type", request.getType());
        }
        queryWrapperUser.orderByDesc("create_time");
        return wqPeopleOrderMapper.selectPage(page, queryWrapperUser);
    }

    @Override
    public WqPeopleOrder getById(WqPeopleOrderDetailDelRequest request) {
        return wqPeopleOrderMapper.selectById(request.getId());
    }

    @Override
    public Boolean delById(WqPeopleOrderDetailDelRequest request) {
        return wqPeopleOrderMapper.deleteById(request.getId()) > 0;
    }

}
