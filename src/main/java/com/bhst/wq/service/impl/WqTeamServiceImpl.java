package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqTeam;
import com.bhst.wq.mapper.WqTeamMapper;
import com.bhst.wq.request.WqTeamDetailDelRequest;
import com.bhst.wq.request.WqTeamPageListRequest;
import com.bhst.wq.service.WqTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WqTeamServiceImpl extends ServiceImpl<WqTeamMapper, WqTeam> implements WqTeamService {

    private final WqTeamMapper wqTeamMapper;


    @Autowired
    public WqTeamServiceImpl(WqTeamMapper wqTeamMapper) {
        this.wqTeamMapper = wqTeamMapper;
    }

    @Override
    public IPage<WqTeam> getPageList(WqTeamPageListRequest request) {
        Page<WqTeam> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqTeam> queryWrapperUser = new QueryWrapper();
        queryWrapperUser.orderByDesc("create_time");
        return wqTeamMapper.selectPage(page, queryWrapperUser);
    }

    @Override
    public WqTeam getById(WqTeamDetailDelRequest request) {
        return wqTeamMapper.selectById(request.getId());
    }

    @Override
    public Boolean delById(WqTeamDetailDelRequest request) {
        return wqTeamMapper.deleteById(request.getId()) > 0;
    }

}
