package com.bhst.wq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhst.wq.entity.WqCivilizedClassroom;
import com.bhst.wq.request.WqCivilizedClassroomDetailDelRequest;
import com.bhst.wq.request.WqCivilizedClassroomPageListRequest;

public interface WqCivilizedClassroomService extends IService<WqCivilizedClassroom> {


    /**
     * 分页获取列表信息
     *
     * @param request
     * @return
     */
    IPage<WqCivilizedClassroom> getPageList(WqCivilizedClassroomPageListRequest request);

    /**
     * @param request
     * @return
     */
    WqCivilizedClassroom getById(WqCivilizedClassroomDetailDelRequest request);

    /**
     * @param request
     * @return
     */
    Boolean delById(WqCivilizedClassroomDetailDelRequest request);

    /**
     * 点赞
     * @param request
     * @return
     */
    Boolean addLikeById(WqCivilizedClassroomDetailDelRequest request);
}
