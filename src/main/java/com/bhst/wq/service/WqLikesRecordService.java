package com.bhst.wq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bhst.wq.entity.WqLikesRecord;
import com.bhst.wq.request.WqLikesRecordQueryRequest;

public interface WqLikesRecordService extends IService<WqLikesRecord> {


    /**
     * 根据类型获取用户是否点赞
     * @param request
     * @return
     */
    Boolean getByUserAndType(WqLikesRecordQueryRequest request);
}
