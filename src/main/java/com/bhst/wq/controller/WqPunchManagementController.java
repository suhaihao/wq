package com.bhst.wq.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhst.wq.entity.WqActivityRecruitment;
import com.bhst.wq.entity.WqPunchManagement;
import com.bhst.wq.entity.WqUser;
import com.bhst.wq.mapper.WqUserMapper;
import com.bhst.wq.request.WqPunchManagementAddRequest;
import com.bhst.wq.request.WqPunchManagementDetailDelRequest;
import com.bhst.wq.request.WqPunchManagementPageListRequest;
import com.bhst.wq.service.WqActivityRecruitmentService;
import com.bhst.wq.service.WqPunchManagementService;
import com.bhst.wq.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping(value = "/punch")
@CrossOrigin
@Api("打卡接口")
public class WqPunchManagementController {

    private final WqPunchManagementService wqPunchManagementService;

    private final WqActivityRecruitmentService wqActivityRecruitmentService;

    private final WqUserMapper wqUserMapper;

    @Autowired
    public WqPunchManagementController(WqPunchManagementService wqPunchManagementService, WqActivityRecruitmentService wqActivityRecruitmentService, WqUserMapper wqUserMapper) {
        this.wqPunchManagementService = wqPunchManagementService;
        this.wqActivityRecruitmentService = wqActivityRecruitmentService;
        this.wqUserMapper = wqUserMapper;
    }

    @PostMapping("/getPageList")
    @ApiOperation(value = "获取打卡列表")
    public IPage<WqPunchManagement> getPageList(@Valid @RequestBody WqPunchManagementPageListRequest request) {
        return wqPunchManagementService.getPageList(request);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加修改打卡")
    public Boolean add(@RequestBody WqPunchManagementAddRequest request) {
        if (null == request.getId()) {
            WqPunchManagement wqPunchManagement = new WqPunchManagement();
            BeanUtils.copyProperties(request, wqPunchManagement);
            wqPunchManagement.setStartTime(LocalDateTime.now());
            wqPunchManagement.setEndTime(LocalDateTime.now());
            wqPunchManagement.setCreateTime(LocalDateTime.now());
            wqPunchManagement.setUpdateTime(LocalDateTime.now());
            wqPunchManagement.setUserId(UserUtils.getUserId());
            WqActivityRecruitment byId = wqActivityRecruitmentService.getById(wqPunchManagement.getActivityId());
            if (null != byId) {
                byId.setTotalNum(byId.getTotalNum() + 1);
                if (request.getStatus().equals("1")) {
                    byId.setParticipateNum(byId.getParticipateNum() + 1);
                }
                wqActivityRecruitmentService.updateById(byId);
            }
            return wqPunchManagementService.saveOrUpdate(wqPunchManagement);
        } else {
            LocalDateTime today_end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
            LocalDateTime today_start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
            QueryWrapper<WqPunchManagement> queryWrapper = new QueryWrapper();
            queryWrapper.eq("activity_id", request.getActivityId());
            queryWrapper.eq("user_id", UserUtils.getUserId());
            queryWrapper.between("create_time", today_start, today_end);
            WqPunchManagement wqPunchManagement = wqPunchManagementService.selectOneByTime(queryWrapper);
            if (null != wqPunchManagement) {
                if (request.getStatus().equals("1")) {
                    wqPunchManagement.setStartTime(LocalDateTime.now());
                    wqPunchManagement.setStatus(request.getStatus());
                    wqPunchManagement.setUpdateTime(LocalDateTime.now());
                    wqPunchManagementService.updateById(wqPunchManagement);
                    WqActivityRecruitment byId = wqActivityRecruitmentService.getById(wqPunchManagement.getActivityId());
                    if (null != byId) {
                        byId.setParticipateNum(byId.getParticipateNum() + 1);
                        wqActivityRecruitmentService.updateById(byId);
                    }
                    return true;
                } else if (request.getStatus().equals("2")) {
                    wqPunchManagement.setEndTime(LocalDateTime.now());
                    wqPunchManagement.setStatus(request.getStatus());
                    wqPunchManagement.setUpdateTime(LocalDateTime.now());
                    Duration duration = Duration.between(wqPunchManagement.getStartTime(), wqPunchManagement.getEndTime());
                    long time = duration.toMillis() / 1000;
                    wqPunchManagement.setDuration(String.valueOf(time));
                    wqPunchManagementService.updateById(wqPunchManagement);
                    //更新用户积分
                    WqUser wqUser = wqUserMapper.selectById(UserUtils.getUserId());
                    if (null != wqUser) {
                        wqUser.setIntegral((int) time / 60 / 60 + 1);
                        wqUser.setServiceDuration(wqUser.getServiceDuration() + time);
                    }
                    wqUserMapper.updateById(wqUser);
                    return true;
                }
            }
        }
        return false;
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

    @PostMapping("/isParticipateActivities")
    @ApiOperation(value = "是否参加打卡活动")
    public WqPunchManagement isParticipateActivities(@Valid @RequestBody WqPunchManagementDetailDelRequest request) {
        LocalDateTime today_end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        LocalDateTime today_start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        QueryWrapper<WqPunchManagement> queryWrapper = new QueryWrapper();
        queryWrapper.eq("activity_id", request.getId());
        queryWrapper.eq("user_id", UserUtils.getUserId());
        queryWrapper.between("create_time", today_start, today_end);
        return wqPunchManagementService.selectOneByTime(queryWrapper);
    }

}
