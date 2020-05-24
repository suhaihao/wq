package com.bhst.wq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhst.wq.entity.WqPeopleOrder;
import com.bhst.wq.request.WqPeopleOrderDetailDelRequest;
import com.bhst.wq.request.WqPeopleOrderPageListRequest;

public interface WqPeopleOrderService extends IService<WqPeopleOrder> {


    /**
     * 分页获取列表信息
     *
     * @param request
     * @return
     */
    IPage<WqPeopleOrder> getPageList(WqPeopleOrderPageListRequest request);

    /**
     * @param request
     * @return
     */
    WqPeopleOrder getById(WqPeopleOrderDetailDelRequest request);

    /**
     * @param request
     * @return
     */
    Boolean delById(WqPeopleOrderDetailDelRequest request);
}
