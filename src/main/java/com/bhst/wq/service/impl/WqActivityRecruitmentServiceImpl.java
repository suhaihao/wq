package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqActivityRecruitment;
import com.bhst.wq.entity.WqLikesRecord;
import com.bhst.wq.mapper.WqActivityRecruitmentMapper;
import com.bhst.wq.request.WqActivityRecruitmentDetailDelRequest;
import com.bhst.wq.request.WqActivityRecruitmentPageListRequest;
import com.bhst.wq.service.WqActivityRecruitmentService;
import com.bhst.wq.service.WqLikesRecordService;
import com.bhst.wq.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

@Service
public class WqActivityRecruitmentServiceImpl extends ServiceImpl<WqActivityRecruitmentMapper, WqActivityRecruitment> implements WqActivityRecruitmentService {

    private final WqActivityRecruitmentMapper wqActivityRecruitmentMapper;

    private final WqLikesRecordService wqLikesRecordService;


    @Autowired
    public WqActivityRecruitmentServiceImpl(WqActivityRecruitmentMapper wqActivityRecruitmentMapper, WqLikesRecordService wqLikesRecordService) {
        this.wqActivityRecruitmentMapper = wqActivityRecruitmentMapper;
        this.wqLikesRecordService = wqLikesRecordService;
    }


    @Override
    public IPage<WqActivityRecruitment> getPageList(WqActivityRecruitmentPageListRequest request) {
        Page<WqActivityRecruitment> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqActivityRecruitment> queryWrapperUser = new QueryWrapper();
        if (null != request.getStatus()) {
            queryWrapperUser.eq("status", request.getStatus());
        }
        if (request.getIsRank()) {
            queryWrapperUser.eq("is_audit", 0);
        } else {
            queryWrapperUser.eq("is_audit", 1);
        }
        if (null != request.getIsUser() && request.getIsUser()) {
            queryWrapperUser.eq("create_by", UserUtils.getUserId());
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
    @Transactional
    public Boolean addLikeActivityRecruitment(WqActivityRecruitmentDetailDelRequest request) {
        WqLikesRecord wqLikesRecord = new WqLikesRecord();
        wqLikesRecord.setType(2);
        wqLikesRecord.setTypeId(request.getId());
        wqLikesRecord.setUserId(UserUtils.getUserId());
        wqLikesRecord.setSize(request.getLikes());
        wqLikesRecordService.save(wqLikesRecord);
        return wqActivityRecruitmentMapper.addLikes(request.getLikes(), request.getId()) > 0;
    }
}
