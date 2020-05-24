package com.bhst.wq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhst.wq.entity.WqPracticeStation;
import com.bhst.wq.request.WqPracticeStationDetailDelRequest;
import com.bhst.wq.request.WqPracticeStationPageListRequest;

public interface WqPracticeStationService extends IService<WqPracticeStation> {


    /**
     * 分页获取列表信息
     *
     * @param request
     * @return
     */
    IPage<WqPracticeStation> getPageList(WqPracticeStationPageListRequest request);

    /**
     * @param request
     * @return
     */
    WqPracticeStation getById(WqPracticeStationDetailDelRequest request);

    /**
     * @param request
     * @return
     */
    Boolean delById(WqPracticeStationDetailDelRequest request);
}
