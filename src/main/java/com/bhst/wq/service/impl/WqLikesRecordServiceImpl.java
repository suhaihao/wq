package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqLikesRecord;
import com.bhst.wq.mapper.WqLikesRecordMapper;
import com.bhst.wq.service.WqLikesRecordService;
import org.springframework.stereotype.Service;

@Service
public class WqLikesRecordServiceImpl extends ServiceImpl<WqLikesRecordMapper, WqLikesRecord> implements WqLikesRecordService {

    private final WqLikesRecordMapper wqLikesRecordMapper;


    public WqLikesRecordServiceImpl(WqLikesRecordMapper wqLikesRecordMapper) {
        this.wqLikesRecordMapper = wqLikesRecordMapper;
    }
}
