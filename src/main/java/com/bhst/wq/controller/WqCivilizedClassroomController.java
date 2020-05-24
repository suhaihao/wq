package com.bhst.wq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhst.wq.entity.WqCivilizedClassroom;
import com.bhst.wq.request.WqCivilizedClassroomAddRequest;
import com.bhst.wq.request.WqCivilizedClassroomDetailDelRequest;
import com.bhst.wq.request.WqCivilizedClassroomPageListRequest;
import com.bhst.wq.service.WqCivilizedClassroomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/classroom")
@CrossOrigin
@Api("文明课堂接口接口")
public class WqCivilizedClassroomController {

    private final WqCivilizedClassroomService wqCivilizedClassroomService;

    @Autowired
    public WqCivilizedClassroomController(WqCivilizedClassroomService wqCivilizedClassroomService) {
        this.wqCivilizedClassroomService = wqCivilizedClassroomService;
    }

    @PostMapping("/getPageList")
    @ApiOperation(value = "获取文明课堂列表")
    public IPage<WqCivilizedClassroom> getPageClockList(@RequestBody WqCivilizedClassroomPageListRequest request) {
        return wqCivilizedClassroomService.getPageList(request);
    }

    @PostMapping("/addWqCivilizedClassroom")
    @ApiOperation(value = "添加修改文明课堂")
    public Boolean addWqActivityRecruitment(@RequestBody WqCivilizedClassroomAddRequest request) {
        WqCivilizedClassroom wqCivilizedClassroom = new WqCivilizedClassroom();
        BeanUtils.copyProperties(request, wqCivilizedClassroom);
        wqCivilizedClassroom.setUpdateTime(LocalDateTime.now());
        return wqCivilizedClassroomService.saveOrUpdate(wqCivilizedClassroom);
    }

    @PostMapping("/delWqCivilizedClassroom")
    @ApiOperation(value = "删除文明课堂")
    public Boolean delWqActivityRecruitment(@Valid @RequestBody WqCivilizedClassroomDetailDelRequest request) {
        return wqCivilizedClassroomService.delById(request);
    }

    @PostMapping("/detailWqActivityRecruitment")
    @ApiOperation(value = "获取文明课堂详情")
    public WqCivilizedClassroom detailWqActivityRecruitment(@Valid @RequestBody WqCivilizedClassroomDetailDelRequest request) {
        return wqCivilizedClassroomService.getById(request);
    }

}
