package com.bhst.wq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhst.wq.entity.WqPunchManagement;
import com.bhst.wq.request.WqPunchManagementAddRequest;
import com.bhst.wq.request.WqPunchManagementDetailDelRequest;
import com.bhst.wq.request.WqPunchManagementPageListRequest;
import com.bhst.wq.service.WqPunchManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/punch")
@CrossOrigin
@Api("打卡接口")
public class WqPunchManagementController {

    private final WqPunchManagementService wqPunchManagementService;

    @Autowired
    public WqPunchManagementController(WqPunchManagementService wqPunchManagementService) {
        this.wqPunchManagementService = wqPunchManagementService;
    }

    @PostMapping("/getPageList")
    @ApiOperation(value = "获取打卡列表")
    public IPage<WqPunchManagement> getPageList(@RequestBody WqPunchManagementPageListRequest request) {
        return wqPunchManagementService.getPageList(request);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加修改打卡")
    public Boolean add(@RequestBody WqPunchManagementAddRequest request) {
        WqPunchManagement WqPunchManagement = new WqPunchManagement();
        BeanUtils.copyProperties(request, WqPunchManagement);
        return wqPunchManagementService.saveOrUpdate(WqPunchManagement);
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除打卡")
    public Boolean del(@Valid @RequestBody WqPunchManagementDetailDelRequest request) {
        return wqPunchManagementService.delById(request);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "获取打卡详情")
    public WqPunchManagement detail(@Valid @RequestBody WqPunchManagementDetailDelRequest request) {
        return wqPunchManagementService.getById(request);
    }

}
