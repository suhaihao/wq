package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqCivilizedClassroom;
import com.bhst.wq.entity.WqLikesRecord;
import com.bhst.wq.mapper.WqCivilizedClassroomMapper;
import com.bhst.wq.request.WqCivilizedClassroomDetailDelRequest;
import com.bhst.wq.request.WqCivilizedClassroomPageListRequest;
import com.bhst.wq.service.WqCivilizedClassroomService;
import com.bhst.wq.service.WqLikesRecordService;
import com.bhst.wq.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WqCivilizedClassroomServiceImpl extends ServiceImpl<WqCivilizedClassroomMapper, WqCivilizedClassroom> implements WqCivilizedClassroomService {

    private final WqCivilizedClassroomMapper wqCivilizedClassroomMapper;
    private final WqLikesRecordService wqLikesRecordService;

    @Autowired
    public WqCivilizedClassroomServiceImpl(WqCivilizedClassroomMapper wqCivilizedClassroomMapper, WqLikesRecordService wqLikesRecordService) {
        this.wqCivilizedClassroomMapper = wqCivilizedClassroomMapper;
        this.wqLikesRecordService = wqLikesRecordService;
    }

    @Override
    public IPage<WqCivilizedClassroom> getPageList(WqCivilizedClassroomPageListRequest request) {
        Page<WqCivilizedClassroom> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqCivilizedClassroom> queryWrapperUser = new QueryWrapper();
        if (null != request.getType()) {
            queryWrapperUser.eq("type", request.getType());
        }
        queryWrapperUser.orderByDesc("create_time");
        return wqCivilizedClassroomMapper.selectPage(page, queryWrapperUser);
    }

    @Override
    public WqCivilizedClassroom getById(WqCivilizedClassroomDetailDelRequest request) {
        return wqCivilizedClassroomMapper.selectById(request.getId());
    }

    @Override
    public Boolean delById(WqCivilizedClassroomDetailDelRequest request) {
        return wqCivilizedClassroomMapper.deleteById(request.getId()) > 0;
    }

    @Override
    public Boolean addLikeById(WqCivilizedClassroomDetailDelRequest request) {
        WqLikesRecord wqLikesRecord = new WqLikesRecord();
        wqLikesRecord.setType(3);
        wqLikesRecord.setTypeId(request.getId());
        wqLikesRecord.setUserId(UserUtils.getUserId());
        wqLikesRecord.setSize(request.getLikes());
        wqLikesRecordService.save(wqLikesRecord);
        return wqCivilizedClassroomMapper.addLikes(request.getLikes(), request.getId()) > 0;
    }

}
