package com.bhst.wq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhst.wq.entity.WqPracticeCenter;
import com.bhst.wq.request.WqPracticeCenterAddRequest;
import com.bhst.wq.request.WqPracticeCenterDetailDelRequest;
import com.bhst.wq.request.WqPracticeCenterPageListRequest;
import com.bhst.wq.service.WqPracticeCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/center")
@CrossOrigin
@Api("实践中心接口")
public class WqPracticeCenterController {

    private final WqPracticeCenterService wqPracticeCenterService;

    @Autowired
    public WqPracticeCenterController(WqPracticeCenterService wqPracticeCenterService) {
        this.wqPracticeCenterService = wqPracticeCenterService;
    }

    @PostMapping("/getPageList")
    @ApiOperation(value = "获取实践中心列表")
    public IPage<WqPracticeCenter> getPageList(@RequestBody WqPracticeCenterPageListRequest request) {
        return wqPracticeCenterService.getPageList(request);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加修改实践中心")
    public Boolean add(@RequestBody WqPracticeCenterAddRequest request) {
        WqPracticeCenter wqPracticeCenter = new WqPracticeCenter();
        BeanUtils.copyProperties(request, wqPracticeCenter);
        return wqPracticeCenterService.saveOrUpdate(wqPracticeCenter);
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除实践中心")
    public Boolean del(@Valid @RequestBody WqPracticeCenterDetailDelRequest request) {
        return wqPracticeCenterService.delById(request);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "获取实践中心详情")
    public WqPracticeCenter detail(@Valid @RequestBody WqPracticeCenterDetailDelRequest request) {
        return wqPracticeCenterService.getById(request);
    }

}
