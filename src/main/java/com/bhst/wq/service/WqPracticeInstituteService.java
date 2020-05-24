package com.bhst.wq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhst.wq.entity.WqPracticeInstitute;
import com.bhst.wq.request.WqPracticeInstituteDetailDelRequest;
import com.bhst.wq.request.WqPracticeInstitutePageListRequest;

public interface WqPracticeInstituteService extends IService<WqPracticeInstitute> {


    /**
     * 分页获取列表信息
     *
     * @param request
     * @return
     */
    IPage<WqPracticeInstitute> getPageList(WqPracticeInstitutePageListRequest request);

    /**
     * @param request
     * @return
     */
    WqPracticeInstitute getById(WqPracticeInstituteDetailDelRequest request);

    /**
     * @param request
     * @return
     */
    Boolean delById(WqPracticeInstituteDetailDelRequest request);
}
