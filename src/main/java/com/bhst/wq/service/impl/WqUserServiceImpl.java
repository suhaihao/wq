package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqUser;
import com.bhst.wq.mapper.WqUserMapper;
import com.bhst.wq.request.WqUserDetailDelRequest;
import com.bhst.wq.request.WqUserPageListRequest;
import com.bhst.wq.service.WqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WqUserServiceImpl extends ServiceImpl<WqUserMapper, WqUser> implements WqUserService {

    private final WqUserMapper wqUserMapper;


    @Autowired
    public WqUserServiceImpl(WqUserMapper wqUserMapper) {
        this.wqUserMapper = wqUserMapper;
    }

    @Override
    public IPage<WqUser> getPageList(WqUserPageListRequest request) {
        Page<WqUser> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqUser> queryWrapperUser = new QueryWrapper();
        queryWrapperUser.orderByDesc("create_time");
        return wqUserMapper.selectPage(page, queryWrapperUser);
    }

    @Override
    public WqUser getById(WqUserDetailDelRequest request) {
        return wqUserMapper.selectById(request.getId());
    }

    @Override
    public Boolean delById(WqUserDetailDelRequest request) {
        return wqUserMapper.deleteById(request.getId()) > 0;
    }

}
