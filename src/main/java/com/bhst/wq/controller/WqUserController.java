package com.bhst.wq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhst.wq.entity.WqUser;
import com.bhst.wq.request.WqTeamPageListRequest;
import com.bhst.wq.request.WqUserAddRequest;
import com.bhst.wq.request.WqUserDetailDelRequest;
import com.bhst.wq.request.WqUserPageListRequest;
import com.bhst.wq.response.WqTeamResponse;
import com.bhst.wq.response.WqUserResponse;
import com.bhst.wq.service.WqUserService;
import com.bhst.wq.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
@Api("用户接口")
public class WqUserController {

    private final WqUserService wqUserService;

    @Autowired
    public WqUserController(WqUserService wqUserService) {
        this.wqUserService = wqUserService;
    }

    @PostMapping("/getPageList")
    @ApiOperation(value = "获取用户列表")
    public IPage<WqUser> getPageList(@RequestBody WqUserPageListRequest request) {
        return wqUserService.getPageList(request);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加修改用户")
    public Boolean add(@RequestBody WqUserAddRequest request) {
        WqUser WqUser = new WqUser();
        BeanUtils.copyProperties(request, WqUser);
        WqUser.setPassword(new BCryptPasswordEncoder().encode(WqUser.getPassword()));
        return wqUserService.saveOrUpdate(WqUser);
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除用户")
    public Boolean del(@Valid @RequestBody WqUserDetailDelRequest request) {
        return wqUserService.delById(request);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "获取用户详情")
    public WqUser detail(@Valid @RequestBody WqUserDetailDelRequest request) {
        return wqUserService.getById(request);
    }

    @PostMapping("/ranking")
    @ApiOperation(value = "获取个人排行")
    public IPage<WqUserResponse> ranking(@Valid @RequestBody WqUserPageListRequest request) {
        return wqUserService.getRankingPageList(request);
    }

    @PostMapping("/leve")
    @ApiOperation(value = "获取登陆用户级别")
    public Integer ranking() {
        return UserUtils.getUserLeve();
    }
}
