package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqCivilizedSupervision;
import com.bhst.wq.mapper.WqCivilizedSupervisionMapper;
import com.bhst.wq.request.WqCivilizedSupervisionDetailDelRequest;
import com.bhst.wq.request.WqCivilizedSupervisionPageListRequest;
import com.bhst.wq.service.WqCivilizedSupervisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WqCivilizedSupervisionServiceImpl extends ServiceImpl<WqCivilizedSupervisionMapper, WqCivilizedSupervision> implements WqCivilizedSupervisionService {

    private final WqCivilizedSupervisionMapper wqCivilizedSupervisionMapper;


    @Autowired
    public WqCivilizedSupervisionServiceImpl(WqCivilizedSupervisionMapper wqCivilizedSupervisionMapper) {
        this.wqCivilizedSupervisionMapper = wqCivilizedSupervisionMapper;
    }

    @Override
    public IPage<WqCivilizedSupervision> getPageList(WqCivilizedSupervisionPageListRequest request) {
        Page<WqCivilizedSupervision> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqCivilizedSupervision> queryWrapperUser = new QueryWrapper();
        queryWrapperUser.orderByDesc("create_time");
        return wqCivilizedSupervisionMapper.selectPage(page, queryWrapperUser);
    }

    @Override
    public WqCivilizedSupervision getById(WqCivilizedSupervisionDetailDelRequest request) {
        return wqCivilizedSupervisionMapper.selectById(request.getId());
    }

    @Override
    public Boolean delById(WqCivilizedSupervisionDetailDelRequest request) {
        return wqCivilizedSupervisionMapper.deleteById(request.getId()) > 0;
    }

}
