package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqActivityRecruitment;
import com.bhst.wq.mapper.WqActivityRecruitmentMapper;
import com.bhst.wq.request.WqActivityRecruitmentDetailDelRequest;
import com.bhst.wq.request.WqActivityRecruitmentPageListRequest;
import com.bhst.wq.service.WqActivityRecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WqActivityRecruitmentServiceImpl extends ServiceImpl<WqActivityRecruitmentMapper, WqActivityRecruitment> implements WqActivityRecruitmentService {

    private final WqActivityRecruitmentMapper wqActivityRecruitmentMapper;


    @Autowired
    public WqActivityRecruitmentServiceImpl(WqActivityRecruitmentMapper wqActivityRecruitmentMapper) {
        this.wqActivityRecruitmentMapper = wqActivityRecruitmentMapper;
    }


    @Override
    public IPage<WqActivityRecruitment> getPageList(WqActivityRecruitmentPageListRequest request) {
        Page<WqActivityRecruitment> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqActivityRecruitment> queryWrapperUser = new QueryWrapper();
        if (null != request.getStatus()) {
            queryWrapperUser.eq("status", request.getStatus());
        }
        queryWrapperUser.orderByDesc("create_time");
        return wqActivityRecruitmentMapper.selectPage(page, queryWrapperUser);
    }

    @Override
    public WqActivityRecruitment getById(WqActivityRecruitmentDetailDelRequest wqActivityRecruitmentDetailDelRequest) {
        return wqActivityRecruitmentMapper.selectById(wqActivityRecruitmentDetailDelRequest.getId());
    }

    @Override
    public Boolean delById(WqActivityRecruitmentDetailDelRequest wqActivityRecruitmentDetailDelRequest) {
        return wqActivityRecruitmentMapper.deleteById(wqActivityRecruitmentDetailDelRequest.getId()) > 0;
    }

    @Override
    public Boolean addLikeActivityRecruitment(WqActivityRecruitmentDetailDelRequest request) {
        return wqActivityRecruitmentMapper.addLikes(request.getId(), request.getLikes()) > 0;
    }
}
