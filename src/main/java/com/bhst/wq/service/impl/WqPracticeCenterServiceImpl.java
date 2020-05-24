package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqPracticeCenter;
import com.bhst.wq.mapper.WqPracticeCenterMapper;
import com.bhst.wq.request.WqPracticeCenterDetailDelRequest;
import com.bhst.wq.request.WqPracticeCenterPageListRequest;
import com.bhst.wq.service.WqPracticeCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WqPracticeCenterServiceImpl extends ServiceImpl<WqPracticeCenterMapper, WqPracticeCenter> implements WqPracticeCenterService {

    private final WqPracticeCenterMapper wqPracticeCenterMapper;


    @Autowired
    public WqPracticeCenterServiceImpl(WqPracticeCenterMapper wqPracticeCenterMapper) {
        this.wqPracticeCenterMapper = wqPracticeCenterMapper;
    }

    @Override
    public IPage<WqPracticeCenter> getPageList(WqPracticeCenterPageListRequest request) {
        Page<WqPracticeCenter> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqPracticeCenter> queryWrapperUser = new QueryWrapper();
        queryWrapperUser.orderByDesc("create_time");
        return wqPracticeCenterMapper.selectPage(page, queryWrapperUser);
    }

    @Override
    public WqPracticeCenter getById(WqPracticeCenterDetailDelRequest request) {
        return wqPracticeCenterMapper.selectById(request.getId());
    }

    @Override
    public Boolean delById(WqPracticeCenterDetailDelRequest request) {
        return wqPracticeCenterMapper.deleteById(request.getId()) > 0;
    }

}
