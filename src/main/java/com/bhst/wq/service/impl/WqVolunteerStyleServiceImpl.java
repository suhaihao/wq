package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqVolunteerStyle;
import com.bhst.wq.mapper.WqVolunteerStyleMapper;
import com.bhst.wq.request.WqVolunteerStyleDetailDelRequest;
import com.bhst.wq.request.WqVolunteerStylePageListRequest;
import com.bhst.wq.service.WqVolunteerStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WqVolunteerStyleServiceImpl extends ServiceImpl<WqVolunteerStyleMapper, WqVolunteerStyle> implements WqVolunteerStyleService {

    private final WqVolunteerStyleMapper wqVolunteerStyleMapper;


    @Autowired
    public WqVolunteerStyleServiceImpl(WqVolunteerStyleMapper wqVolunteerStyleMapper) {
        this.wqVolunteerStyleMapper = wqVolunteerStyleMapper;
    }

    @Override
    public IPage<WqVolunteerStyle> getPageList(WqVolunteerStylePageListRequest request) {
        Page<WqVolunteerStyle> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqVolunteerStyle> queryWrapperUser = new QueryWrapper();
        if (null != request.getType()) {
            queryWrapperUser.eq("type", request.getType());
        }
        queryWrapperUser.orderByDesc("create_time");
        return wqVolunteerStyleMapper.selectPage(page, queryWrapperUser);
    }

    @Override
    public WqVolunteerStyle getById(WqVolunteerStyleDetailDelRequest request) {
        return wqVolunteerStyleMapper.selectById(request.getId());
    }

    @Override
    public Boolean delById(WqVolunteerStyleDetailDelRequest request) {
        return wqVolunteerStyleMapper.deleteById(request.getId()) > 0;
    }

}
