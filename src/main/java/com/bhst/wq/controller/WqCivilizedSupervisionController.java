package com.bhst.wq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhst.wq.entity.WqCivilizedSupervision;
import com.bhst.wq.entity.WqLikesRecord;
import com.bhst.wq.request.WqCivilizedSupervisionAddRequest;
import com.bhst.wq.request.WqCivilizedSupervisionDetailDelRequest;
import com.bhst.wq.request.WqCivilizedSupervisionPageListRequest;
import com.bhst.wq.service.WqCivilizedSupervisionService;
import com.bhst.wq.service.WqLikesRecordService;
import com.bhst.wq.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/supervisor")
@CrossOrigin
@Api("文明监督接口")
public class WqCivilizedSupervisionController {

    private final WqCivilizedSupervisionService wqCivilizedSupervisionService;
    private final WqLikesRecordService wqLikesRecordService;

    @Autowired
    public WqCivilizedSupervisionController(WqCivilizedSupervisionService wqCivilizedSupervisionService, WqLikesRecordService wqLikesRecordService) {
        this.wqCivilizedSupervisionService = wqCivilizedSupervisionService;
        this.wqLikesRecordService = wqLikesRecordService;
    }

    @PostMapping("/getPageList")
    @ApiOperation(value = "获取文明监督列表")
    public IPage<WqCivilizedSupervision> getPageList(@RequestBody WqCivilizedSupervisionPageListRequest request) {
        return wqCivilizedSupervisionService.getPageList(request);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加修改文明监督")
    public Boolean add(@RequestBody WqCivilizedSupervisionAddRequest request) {
        WqCivilizedSupervision WqCivilizedSupervision = new WqCivilizedSupervision();
        BeanUtils.copyProperties(request, WqCivilizedSupervision);
        return wqCivilizedSupervisionService.saveOrUpdate(WqCivilizedSupervision);
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除文明监督")
    public Boolean del(@Valid @RequestBody WqCivilizedSupervisionDetailDelRequest request) {
        return wqCivilizedSupervisionService.delById(request);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "获取文明监督详情")
    public WqCivilizedSupervision detail(@Valid @RequestBody WqCivilizedSupervisionDetailDelRequest request) {
        return wqCivilizedSupervisionService.getById(request);
    }

    @PostMapping("/addLike")
    @ApiOperation(value = "点赞")
    public Boolean addLike(@Valid @RequestBody WqCivilizedSupervisionDetailDelRequest request) {
        WqLikesRecord wqLikesRecord = new WqLikesRecord();
        wqLikesRecord.setType(1);
        wqLikesRecord.setTypeId(request.getId());
        wqLikesRecord.setUserId(UserUtils.getUserId());
        wqLikesRecord.setSize(request.getDislikes());
        wqLikesRecordService.save(wqLikesRecord);
        return wqCivilizedSupervisionService.addDislikes(request);
    }

}
