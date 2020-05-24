package com.bhst.wq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhst.wq.entity.WqFinancialMedia;
import com.bhst.wq.request.WqFinanciaMediaDetailDelRequest;
import com.bhst.wq.request.WqFinanciaMediaPageListRequest;
import com.bhst.wq.request.WqFinancialMediaAddRequest;
import com.bhst.wq.service.WqFinanciaMediaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/media")
@CrossOrigin
@Api("溶媒接口")
public class WqFinanciaMediaController {

    private final WqFinanciaMediaService wqFinanciaMediaService;

    @Autowired
    public WqFinanciaMediaController(WqFinanciaMediaService wqFinanciaMediaService) {
        this.wqFinanciaMediaService = wqFinanciaMediaService;
    }

    @PostMapping("/getPageList")
    @ApiOperation(value = "获取溶媒列表")
    public IPage<WqFinancialMedia> getPageClockList(@RequestBody WqFinanciaMediaPageListRequest request) {
        return wqFinanciaMediaService.getPageList(request);
    }

    @PostMapping("/addWqFinancialMedia")
    @ApiOperation(value = "添加修改溶媒")
    public Boolean addWqFinancialMedia(@RequestBody WqFinancialMediaAddRequest request) {
        WqFinancialMedia wqFinancialMedia = new WqFinancialMedia();
        BeanUtils.copyProperties(request, wqFinancialMedia);
        wqFinancialMedia.setUpdateTime(LocalDateTime.now());
        return wqFinanciaMediaService.saveOrUpdate(wqFinancialMedia);
    }

    @PostMapping("/delWqFinancialMedia")
    @ApiOperation(value = "删除溶媒")
    public Boolean delWqFinancialMedia(@Valid @RequestBody WqFinanciaMediaDetailDelRequest request) {
        return wqFinanciaMediaService.delById(request);
    }

    @PostMapping("/detailWqFinancialMedia")
    @ApiOperation(value = "获取溶媒详情")
    public WqFinancialMedia detailWqFinancialMedia(@Valid @RequestBody WqFinanciaMediaDetailDelRequest request) {
        return wqFinanciaMediaService.getById(request);
    }

}
