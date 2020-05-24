package com.bhst.wq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhst.wq.entity.WqRegionalCharacteristics;
import com.bhst.wq.request.WqRegionalCharacteristicsAddRequest;
import com.bhst.wq.request.WqRegionalCharacteristicsDetailDelRequest;
import com.bhst.wq.request.WqRegionalCharacteristicsPageListRequest;
import com.bhst.wq.service.WqRegionalCharacteristicsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/regional")
@CrossOrigin
@Api("风俗接口")
public class WqRegionalCharacteristicsController {

    private final WqRegionalCharacteristicsService wqRegionalCharacteristicsService;

    @Autowired
    public WqRegionalCharacteristicsController(WqRegionalCharacteristicsService wqRegionalCharacteristicsService) {
        this.wqRegionalCharacteristicsService = wqRegionalCharacteristicsService;
    }

    @PostMapping("/getPageList")
    @ApiOperation(value = "获取风俗列表")
    public IPage<WqRegionalCharacteristics> getPageList(@RequestBody WqRegionalCharacteristicsPageListRequest request) {
        return wqRegionalCharacteristicsService.getPageList(request);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加修改风俗")
    public Boolean add(@RequestBody WqRegionalCharacteristicsAddRequest request) {
        WqRegionalCharacteristics WqRegionalCharacteristics = new WqRegionalCharacteristics();
        BeanUtils.copyProperties(request, WqRegionalCharacteristics);
        return wqRegionalCharacteristicsService.saveOrUpdate(WqRegionalCharacteristics);
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除风俗")
    public Boolean del(@Valid @RequestBody WqRegionalCharacteristicsDetailDelRequest request) {
        return wqRegionalCharacteristicsService.delById(request);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "获取风俗详情")
    public WqRegionalCharacteristics detail(@Valid @RequestBody WqRegionalCharacteristicsDetailDelRequest request) {
        return wqRegionalCharacteristicsService.getById(request);
    }

}
