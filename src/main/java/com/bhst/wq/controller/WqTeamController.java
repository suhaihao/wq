package com.bhst.wq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhst.wq.entity.WqTeam;
import com.bhst.wq.request.WqTeamAddRequest;
import com.bhst.wq.request.WqTeamDetailDelRequest;
import com.bhst.wq.request.WqTeamPageListRequest;
import com.bhst.wq.response.WqTeamResponse;
import com.bhst.wq.service.WqTeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/team")
@CrossOrigin
@Api("团队接口")
public class WqTeamController {

    private final WqTeamService wqTeamService;

    @Autowired
    public WqTeamController(WqTeamService wqTeamService) {
        this.wqTeamService = wqTeamService;
    }

    @PostMapping("/getPageList")
    @ApiOperation(value = "获取团队列表")
    public IPage<WqTeam> getPageList(@RequestBody WqTeamPageListRequest request) {
        return wqTeamService.getPageList(request);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加修改团队")
    public Boolean add(@RequestBody WqTeamAddRequest request) {
        WqTeam WqTeam = new WqTeam();
        BeanUtils.copyProperties(request, WqTeam);
        return wqTeamService.saveOrUpdate(WqTeam);
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除团队")
    public Boolean del(@Valid @RequestBody WqTeamDetailDelRequest request) {
        return wqTeamService.delById(request);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "获取团队详情")
    public WqTeam detail(@Valid @RequestBody WqTeamDetailDelRequest request) {
        return wqTeamService.getById(request);
    }

    @PostMapping("/ranking")
    @ApiOperation(value = "获取团队排行")
    public IPage<WqTeamResponse> ranking(@Valid @RequestBody WqTeamPageListRequest request) {
        return wqTeamService.getByRanking(request);
    }

}
