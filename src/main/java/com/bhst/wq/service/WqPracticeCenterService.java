package com.bhst.wq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhst.wq.entity.WqPracticeCenter;
import com.bhst.wq.request.WqPracticeCenterDetailDelRequest;
import com.bhst.wq.request.WqPracticeCenterPageListRequest;

public interface WqPracticeCenterService extends IService<WqPracticeCenter> {


    /**
     * 分页获取列表信息
     *
     * @param request
     * @return
     */
    IPage<WqPracticeCenter> getPageList(WqPracticeCenterPageListRequest request);

    /**
     * @param request
     * @return
     */
    WqPracticeCenter getById(WqPracticeCenterDetailDelRequest request);

    /**
     * @param request
     * @return
     */
    Boolean delById(WqPracticeCenterDetailDelRequest request);
}
