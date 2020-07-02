package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqLikesRecord;
import com.bhst.wq.mapper.WqLikesRecordMapper;
import com.bhst.wq.request.WqLikesRecordQueryRequest;
import com.bhst.wq.service.WqLikesRecordService;
import com.bhst.wq.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class WqLikesRecordServiceImpl extends ServiceImpl<WqLikesRecordMapper, WqLikesRecord> implements WqLikesRecordService {

    private final WqLikesRecordMapper wqLikesRecordMapper;


    public WqLikesRecordServiceImpl(WqLikesRecordMapper wqLikesRecordMapper) {
        this.wqLikesRecordMapper = wqLikesRecordMapper;
    }

    @Override
    public Boolean getByUserAndType(WqLikesRecordQueryRequest request) {
        QueryWrapper<WqLikesRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("sum(size) size");
        queryWrapper.eq("type", request.getType());
        queryWrapper.eq("type_id", request.getTypeId());
        queryWrapper.eq("user_id", UserUtils.getUserId());
        WqLikesRecord wqLikesRecord = wqLikesRecordMapper.selectOne(queryWrapper);
        return wqLikesRecord == null ? false : wqLikesRecord.getSize() > 0;
    }
}
