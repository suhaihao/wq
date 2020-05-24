package com.bhst.wq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhst.wq.entity.WqPracticeStation;
import com.bhst.wq.request.WqPracticeStationAddRequest;
import com.bhst.wq.request.WqPracticeStationDetailDelRequest;
import com.bhst.wq.request.WqPracticeStationPageListRequest;
import com.bhst.wq.service.WqPracticeStationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/station")
@CrossOrigin
@Api("实践阵地接口")
public class WqPracticeStationController {

    private final WqPracticeStationService wqPracticeStationService;

    @Autowired
    public WqPracticeStationController(WqPracticeStationService wqPracticeStationService) {
        this.wqPracticeStationService = wqPracticeStationService;
    }

    @PostMapping("/getPageList")
    @ApiOperation(value = "获取实践阵地列表")
    public IPage<WqPracticeStation> getPageList(@RequestBody WqPracticeStationPageListRequest request) {
        return wqPracticeStationService.getPageList(request);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加修改实践阵地")
    public Boolean add(@RequestBody WqPracticeStationAddRequest request) {
        WqPracticeStation WqPracticeStation = new WqPracticeStation();
        BeanUtils.copyProperties(request, WqPracticeStation);
        return wqPracticeStationService.saveOrUpdate(WqPracticeStation);
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除实践阵地")
    public Boolean del(@Valid @RequestBody WqPracticeStationDetailDelRequest request) {
        return wqPracticeStationService.delById(request);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "获取实践阵地详情")
    public WqPracticeStation detail(@Valid @RequestBody WqPracticeStationDetailDelRequest request) {
        return wqPracticeStationService.getById(request);
    }

}
