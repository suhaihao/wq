package com.bhst.wq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhst.wq.entity.WqActivityRecruitment;
import com.bhst.wq.entity.WqUser;
import com.bhst.wq.request.WqActivityRecruitmentAddRequest;
import com.bhst.wq.request.WqActivityRecruitmentDetailDelRequest;
import com.bhst.wq.request.WqActivityRecruitmentPageListRequest;
import com.bhst.wq.service.WqActivityRecruitmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/activity")
@CrossOrigin
@Api("活动接口")
public class WqActivityRecruitmentController {

    private final WqActivityRecruitmentService wqActivityRecruitmentService;

    @Autowired
    public WqActivityRecruitmentController(WqActivityRecruitmentService wqActivityRecruitmentService) {
        this.wqActivityRecruitmentService = wqActivityRecruitmentService;
    }

    @PostMapping("/getPageList")
    @ApiOperation(value = "获取当前活动列表")
    public IPage<WqActivityRecruitment> getPageClockList(@RequestBody WqActivityRecruitmentPageListRequest request) {
        return wqActivityRecruitmentService.getPageList(request);
    }

    @PostMapping("/addWqActivityRecruitment")
    @ApiOperation(value = "添加修改活动")
    public Boolean addWqActivityRecruitment(@RequestBody WqActivityRecruitmentAddRequest request) {
        WqActivityRecruitment wqActivityRecruitment = new WqActivityRecruitment();
        BeanUtils.copyProperties(request, wqActivityRecruitment);
        wqActivityRecruitment.setUpdateTiem(LocalDateTime.now());
        return wqActivityRecruitmentService.saveOrUpdate(wqActivityRecruitment);
    }

    @PostMapping("/delWqActivityRecruitment")
    @ApiOperation(value = "删除修改活动")
    public Boolean delWqActivityRecruitment(@Valid @RequestBody WqActivityRecruitmentDetailDelRequest request) {
        return wqActivityRecruitmentService.delById(request);
    }

    @PostMapping("/detailWqActivityRecruitment")
    @ApiOperation(value = "获取活动详情")
    public WqActivityRecruitment detailWqActivityRecruitment(@Valid @RequestBody WqActivityRecruitmentDetailDelRequest request) {
        return wqActivityRecruitmentService.getById(request);
    }

    @PostMapping("/like")
    @ApiOperation(value = "给活动点赞")
    public Boolean likeWqActivityRecruitment(@Valid @RequestBody WqActivityRecruitmentDetailDelRequest request) {
        return wqActivityRecruitmentService.addLikeActivityRecruitment(request);
    }

}
