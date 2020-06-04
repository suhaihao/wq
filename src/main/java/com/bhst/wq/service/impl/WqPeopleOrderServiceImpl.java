package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqPeopleOrder;
import com.bhst.wq.entity.WqTeam;
import com.bhst.wq.entity.WqUser;
import com.bhst.wq.mapper.WqPeopleOrderMapper;
import com.bhst.wq.mapper.WqTeamMapper;
import com.bhst.wq.mapper.WqUserMapper;
import com.bhst.wq.request.WqPeopleOrderDetailDelRequest;
import com.bhst.wq.request.WqPeopleOrderPageListRequest;
import com.bhst.wq.response.WqPeopleOrderResponse;
import com.bhst.wq.service.WqPeopleOrderService;
import com.bhst.wq.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WqPeopleOrderServiceImpl extends ServiceImpl<WqPeopleOrderMapper, WqPeopleOrder> implements WqPeopleOrderService {

    private final WqPeopleOrderMapper wqPeopleOrderMapper;
    private final WqUserMapper wqUserMapper;
    private final WqTeamMapper wqTeamMapper;


    @Autowired
    public WqPeopleOrderServiceImpl(WqPeopleOrderMapper wqPeopleOrderMapper, WqUserMapper wqUserMapper, WqTeamMapper wqTeamMapper) {
        this.wqPeopleOrderMapper = wqPeopleOrderMapper;
        this.wqUserMapper = wqUserMapper;
        this.wqTeamMapper = wqTeamMapper;
    }

    @Override
    public IPage<WqPeopleOrder> getPageList(WqPeopleOrderPageListRequest request) {
        Page<WqPeopleOrder> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqPeopleOrder> queryWrapperUser = new QueryWrapper();
        if (null != request.getType()) {
            queryWrapperUser.eq("type", request.getType());
        }
        if (request.getIsRank()) {
            queryWrapperUser.eq("is_audit", 0);
        } else {
            queryWrapperUser.eq("is_audit", 1);
        }
        if (null != request.getCreateBy() && request.getCreateBy()) {
            queryWrapperUser.eq("create_by", UserUtils.getUserId());
        }
        if (null != request.getIsUser() && request.getIsUser()) {
            queryWrapperUser.eq("user_id", UserUtils.getUserId());
        }
        queryWrapperUser.orderByDesc("create_time");
        return wqPeopleOrderMapper.selectPage(page, queryWrapperUser);
    }

    @Override
    public WqPeopleOrderResponse getById(WqPeopleOrderDetailDelRequest request) {
        WqPeopleOrderResponse wqPeopleOrderResponse = new WqPeopleOrderResponse();
        WqPeopleOrder wqPeopleOrder = wqPeopleOrderMapper.selectById(request.getId());
        BeanUtils.copyProperties(wqPeopleOrder, wqPeopleOrderResponse);
        WqUser wqUser = wqUserMapper.selectById(wqPeopleOrder.getUserId());
        if (null != wqUser) {
            wqPeopleOrderResponse.setFullname(wqUser.getFullname());
            WqTeam wqTeam = wqTeamMapper.selectById(wqUser.getTeam());
            if (null != wqTeam) {
                wqPeopleOrderResponse.setTeamName(wqTeam.getTeamName());
            }
        }
        return wqPeopleOrderResponse;
    }

    @Override
    public Boolean delById(WqPeopleOrderDetailDelRequest request) {
        return wqPeopleOrderMapper.deleteById(request.getId()) > 0;
    }

}
