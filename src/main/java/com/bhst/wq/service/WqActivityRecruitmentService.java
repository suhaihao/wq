package com.bhst.wq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhst.wq.entity.WqActivityRecruitment;
import com.bhst.wq.request.WqActivityRecruitmentDetailDelRequest;
import com.bhst.wq.request.WqActivityRecruitmentPageListRequest;

public interface WqActivityRecruitmentService extends IService<WqActivityRecruitment> {

    /**
     * 分页获取列表信息
     *
     * @param request
     * @return
     */
    IPage<WqActivityRecruitment> getPageList(WqActivityRecruitmentPageListRequest request);

    /**
     * @param wqActivityRecruitmentDetailDelRequest
     * @return
     */
    WqActivityRecruitment getById(WqActivityRecruitmentDetailDelRequest wqActivityRecruitmentDetailDelRequest);

    /**
     * @param wqActivityRecruitmentDetailDelRequest
     * @return
     */
    Boolean delById(WqActivityRecruitmentDetailDelRequest wqActivityRecruitmentDetailDelRequest);
}
