package com.bhst.wq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhst.wq.entity.WqCivilizedSupervision;
import com.bhst.wq.request.WqCivilizedSupervisionDetailDelRequest;
import com.bhst.wq.request.WqCivilizedSupervisionPageListRequest;

public interface WqCivilizedSupervisionService extends IService<WqCivilizedSupervision> {


    /**
     * 分页获取列表信息
     *
     * @param request
     * @return
     */
    IPage<WqCivilizedSupervision> getPageList(WqCivilizedSupervisionPageListRequest request);

    /**
     * @param request
     * @return
     */
    WqCivilizedSupervision getById(WqCivilizedSupervisionDetailDelRequest request);

    /**
     * @param request
     * @return
     */
    Boolean delById(WqCivilizedSupervisionDetailDelRequest request);
}
