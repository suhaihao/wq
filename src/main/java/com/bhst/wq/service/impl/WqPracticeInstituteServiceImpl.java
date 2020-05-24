package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqPracticeInstitute;
import com.bhst.wq.mapper.WqPracticeInstituteMapper;
import com.bhst.wq.request.WqPracticeInstituteDetailDelRequest;
import com.bhst.wq.request.WqPracticeInstitutePageListRequest;
import com.bhst.wq.service.WqPracticeInstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WqPracticeInstituteServiceImpl extends ServiceImpl<WqPracticeInstituteMapper, WqPracticeInstitute> implements WqPracticeInstituteService {

    private final WqPracticeInstituteMapper wqPracticeInstituteMapper;


    @Autowired
    public WqPracticeInstituteServiceImpl(WqPracticeInstituteMapper wqPracticeInstituteMapper) {
        this.wqPracticeInstituteMapper = wqPracticeInstituteMapper;
    }

    @Override
    public IPage<WqPracticeInstitute> getPageList(WqPracticeInstitutePageListRequest request) {
        Page<WqPracticeInstitute> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqPracticeInstitute> queryWrapperUser = new QueryWrapper();
        if (null != request.getCenterId()) {
            queryWrapperUser.eq("center_id", request.getCenterId());
        }
        queryWrapperUser.orderByDesc("create_time");
        return wqPracticeInstituteMapper.selectPage(page, queryWrapperUser);
    }

    @Override
    public WqPracticeInstitute getById(WqPracticeInstituteDetailDelRequest request) {
        return wqPracticeInstituteMapper.selectById(request.getId());
    }

    @Override
    public Boolean delById(WqPracticeInstituteDetailDelRequest request) {
        return wqPracticeInstituteMapper.deleteById(request.getId()) > 0;
    }

}
