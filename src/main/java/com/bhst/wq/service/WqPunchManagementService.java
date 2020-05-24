package com.bhst.wq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhst.wq.entity.WqPunchManagement;
import com.bhst.wq.request.WqPunchManagementDetailDelRequest;
import com.bhst.wq.request.WqPunchManagementPageListRequest;

public interface WqPunchManagementService extends IService<WqPunchManagement> {


    /**
     * 分页获取列表信息
     *
     * @param request
     * @return
     */
    IPage<WqPunchManagement> getPageList(WqPunchManagementPageListRequest request);

    /**
     * @param request
     * @return
     */
    WqPunchManagement getById(WqPunchManagementDetailDelRequest request);

    /**
     * @param request
     * @return
     */
    Boolean delById(WqPunchManagementDetailDelRequest request);
}
