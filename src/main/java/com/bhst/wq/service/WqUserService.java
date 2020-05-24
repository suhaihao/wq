package com.bhst.wq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhst.wq.entity.WqUser;
import com.bhst.wq.request.WqUserDetailDelRequest;
import com.bhst.wq.request.WqUserPageListRequest;
import com.bhst.wq.response.WqUserResponse;

public interface WqUserService extends IService<WqUser> {


    /**
     * 分页获取列表信息
     *
     * @param request
     * @return
     */
    IPage<WqUser> getPageList(WqUserPageListRequest request);

    /**
     * @param request
     * @return
     */
    WqUser getById(WqUserDetailDelRequest request);

    /**
     * @param request
     * @return
     */
    Boolean delById(WqUserDetailDelRequest request);

    /**
     * 分页获取列表信息
     *
     * @param request
     * @return
     */
    IPage<WqUserResponse> getRankingPageList(WqUserPageListRequest request);
}
