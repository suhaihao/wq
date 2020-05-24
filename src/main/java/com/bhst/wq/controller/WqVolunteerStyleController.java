package com.bhst.wq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhst.wq.entity.WqVolunteerStyle;
import com.bhst.wq.request.WqVolunteerStyleAddRequest;
import com.bhst.wq.request.WqVolunteerStyleDetailDelRequest;
import com.bhst.wq.request.WqVolunteerStylePageListRequest;
import com.bhst.wq.service.WqVolunteerStyleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/volunteer")
@CrossOrigin
@Api("志愿风采接口")
public class WqVolunteerStyleController {

    private final WqVolunteerStyleService wqVolunteerStyleService;

    @Autowired
    public WqVolunteerStyleController(WqVolunteerStyleService wqVolunteerStyleService) {
        this.wqVolunteerStyleService = wqVolunteerStyleService;
    }

    @PostMapping("/getPageList")
    @ApiOperation(value = "获取志愿风采列表")
    public IPage<WqVolunteerStyle> getPageList(@RequestBody WqVolunteerStylePageListRequest request) {
        return wqVolunteerStyleService.getPageList(request);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加修改志愿风采")
    public Boolean add(@RequestBody WqVolunteerStyleAddRequest request) {
        WqVolunteerStyle WqVolunteerStyle = new WqVolunteerStyle();
        BeanUtils.copyProperties(request, WqVolunteerStyle);
        return wqVolunteerStyleService.saveOrUpdate(WqVolunteerStyle);
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除志愿风采")
    public Boolean del(@Valid @RequestBody WqVolunteerStyleDetailDelRequest request) {
        return wqVolunteerStyleService.delById(request);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "获取志愿风采详情")
    public WqVolunteerStyle detail(@Valid @RequestBody WqVolunteerStyleDetailDelRequest request) {
        return wqVolunteerStyleService.getById(request);
    }

}
