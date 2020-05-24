package com.bhst.wq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhst.wq.entity.WqPracticeInstitute;
import com.bhst.wq.request.WqPracticeInstituteAddRequest;
import com.bhst.wq.request.WqPracticeInstituteDetailDelRequest;
import com.bhst.wq.request.WqPracticeInstitutePageListRequest;
import com.bhst.wq.service.WqPracticeInstituteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/institute")
@CrossOrigin
@Api("实践所接口")
public class WqPracticeInstituteController {

    private final WqPracticeInstituteService wqPracticeInstituteService;

    @Autowired
    public WqPracticeInstituteController(WqPracticeInstituteService wqPracticeInstituteService) {
        this.wqPracticeInstituteService = wqPracticeInstituteService;
    }

    @PostMapping("/getPageList")
    @ApiOperation(value = "获取实践所列表")
    public IPage<WqPracticeInstitute> getPageList(@RequestBody WqPracticeInstitutePageListRequest request) {
        return wqPracticeInstituteService.getPageList(request);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加修改实践所")
    public Boolean add(@RequestBody WqPracticeInstituteAddRequest request) {
        WqPracticeInstitute wqPracticeInstitute = new WqPracticeInstitute();
        BeanUtils.copyProperties(request, wqPracticeInstitute);
        return wqPracticeInstituteService.saveOrUpdate(wqPracticeInstitute);
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除实践所")
    public Boolean del(@Valid @RequestBody WqPracticeInstituteDetailDelRequest request) {
        return wqPracticeInstituteService.delById(request);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "获取实践所详情")
    public WqPracticeInstitute detail(@Valid @RequestBody WqPracticeInstituteDetailDelRequest request) {
        return wqPracticeInstituteService.getById(request);
    }

}
