package com.bhst.wq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhst.wq.entity.WqFinancialMedia;
import com.bhst.wq.request.WqFinanciaMediaDetailDelRequest;
import com.bhst.wq.request.WqFinanciaMediaPageListRequest;

public interface WqFinanciaMediaService extends IService<WqFinancialMedia> {


    /**
     * 分页获取列表信息
     *
     * @param request
     * @return
     */
    IPage<WqFinancialMedia> getPageList(WqFinanciaMediaPageListRequest request);

    /**
     * @param request
     * @return
     */
    WqFinancialMedia getById(WqFinanciaMediaDetailDelRequest request);

    /**
     * @param request
     * @return
     */
    Boolean delById(WqFinanciaMediaDetailDelRequest request);
}
