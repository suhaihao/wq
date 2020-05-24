package com.bhst.wq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhst.wq.entity.WqRegionalCharacteristics;
import com.bhst.wq.request.WqRegionalCharacteristicsDetailDelRequest;
import com.bhst.wq.request.WqRegionalCharacteristicsPageListRequest;

public interface WqRegionalCharacteristicsService extends IService<WqRegionalCharacteristics> {


    /**
     * 分页获取列表信息
     *
     * @param request
     * @return
     */
    IPage<WqRegionalCharacteristics> getPageList(WqRegionalCharacteristicsPageListRequest request);

    /**
     * @param request
     * @return
     */
    WqRegionalCharacteristics getById(WqRegionalCharacteristicsDetailDelRequest request);

    /**
     * @param request
     * @return
     */
    Boolean delById(WqRegionalCharacteristicsDetailDelRequest request);
}
