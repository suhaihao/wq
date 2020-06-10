package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqActivityRecruitment;
import com.bhst.wq.entity.WqPunchManagement;
import com.bhst.wq.entity.WqUser;
import com.bhst.wq.mapper.WqActivityRecruitmentMapper;
import com.bhst.wq.mapper.WqPunchManagementMapper;
import com.bhst.wq.mapper.WqUserMapper;
import com.bhst.wq.request.WqPunchManagementDetailDelRequest;
import com.bhst.wq.request.WqPunchManagementPageListRequest;
import com.bhst.wq.service.WqPunchManagementService;
import com.bhst.wq.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WqPunchManagementServiceImpl extends ServiceImpl<WqPunchManagementMapper, WqPunchManagement> implements WqPunchManagementService {

    private final WqPunchManagementMapper wqPunchManagementMapper;
    private final WqUserMapper wqUserMapper;
    private final WqActivityRecruitmentMapper wqActivityRecruitmentMapper;


    @Autowired
    public WqPunchManagementServiceImpl(WqPunchManagementMapper wqPunchManagementMapper, WqUserMapper wqUserMapper, WqActivityRecruitmentMapper wqActivityRecruitmentMapper) {
        this.wqPunchManagementMapper = wqPunchManagementMapper;
        this.wqUserMapper = wqUserMapper;
        this.wqActivityRecruitmentMapper = wqActivityRecruitmentMapper;
    }

    @Override
    public IPage<WqPunchManagement> getPageList(WqPunchManagementPageListRequest request) {
        Page<WqPunchManagement> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqPunchManagement> queryWrapperUser = new QueryWrapper();
        queryWrapperUser.eq("user_id", UserUtils.getUserId());
        queryWrapperUser.orderByDesc("create_time");
        if (null != request.getActivityId()) {
            queryWrapperUser.eq("activity_id", request.getActivityId());
        }
        return wqPunchManagementMapper.selectPage(page, queryWrapperUser);
    }

    @Override
    public IPage<WqUser> getUserPageList(WqPunchManagementPageListRequest request) {
        QueryWrapper<WqPunchManagement> queryWrapper = new QueryWrapper();
        queryWrapper.eq("activity_id", request.getActivityId());
        queryWrapper.groupBy("user_id");
        queryWrapper.select("user_id");
        List<WqPunchManagement> wqPunchManagements = wqPunchManagementMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(wqPunchManagements)) {
            List<Integer> collect = wqPunchManagements.stream().map(WqPunchManagement::getUserId).collect(Collectors.toList());
            Page<WqUser> page = new Page<>(request.getPageIndex(), request.getPageSize());
            QueryWrapper<WqUser> queryUserWrapper = new QueryWrapper();
            queryUserWrapper.in("id", collect);
            return wqUserMapper.selectPage(page, queryUserWrapper);
        }
        return null;
    }

    @Override
    public WqPunchManagement getById(WqPunchManagementDetailDelRequest request) {
        return wqPunchManagementMapper.selectById(request.getId());
    }

    @Override
    public Boolean delById(WqPunchManagementDetailDelRequest request) {
        return wqPunchManagementMapper.deleteById(request.getId()) > 0;
    }

    @Override
    public WqPunchManagement selectOneByTime(QueryWrapper<WqPunchManagement> queryWrapper) {
        return wqPunchManagementMapper.selectOne(queryWrapper);
    }

    @Override
    public List<WqActivityRecruitment> getByUserActivity(WqPunchManagementPageListRequest request) {
        QueryWrapper<WqPunchManagement> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", UserUtils.getUserId());
        if (StringUtils.isEmpty(request.getSignUp())) {
            queryWrapper.eq("status", request.getSignUp());
        }
        queryWrapper.groupBy("activity_id");
        queryWrapper.select("activity_id");
        List<WqPunchManagement> wqPunchManagements = wqPunchManagementMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(wqPunchManagements)) {
            List<Integer> collect = wqPunchManagements.stream().map(WqPunchManagement::getActivityId).collect(Collectors.toList());
            return wqActivityRecruitmentMapper.selectBatchIds(collect);
        }
        return null;
    }

}
