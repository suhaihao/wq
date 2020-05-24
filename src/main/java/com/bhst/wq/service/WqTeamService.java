package com.bhst.wq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhst.wq.entity.WqTeam;
import com.bhst.wq.request.WqTeamDetailDelRequest;
import com.bhst.wq.request.WqTeamPageListRequest;

public interface WqTeamService extends IService<WqTeam> {


    /**
     * 分页获取列表信息
     *
     * @param request
     * @return
     */
    IPage<WqTeam> getPageList(WqTeamPageListRequest request);

    /**
     * @param request
     * @return
     */
    WqTeam getById(WqTeamDetailDelRequest request);

    /**
     * @param request
     * @return
     */
    Boolean delById(WqTeamDetailDelRequest request);
}
