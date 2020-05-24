package com.bhst.wq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhst.wq.entity.WqVolunteerStyle;
import com.bhst.wq.request.WqVolunteerStyleDetailDelRequest;
import com.bhst.wq.request.WqVolunteerStylePageListRequest;

public interface WqVolunteerStyleService extends IService<WqVolunteerStyle> {


    /**
     * 分页获取列表信息
     *
     * @param request
     * @return
     */
    IPage<WqVolunteerStyle> getPageList(WqVolunteerStylePageListRequest request);

    /**
     * @param request
     * @return
     */
    WqVolunteerStyle getById(WqVolunteerStyleDetailDelRequest request);

    /**
     * @param request
     * @return
     */
    Boolean delById(WqVolunteerStyleDetailDelRequest request);
}
