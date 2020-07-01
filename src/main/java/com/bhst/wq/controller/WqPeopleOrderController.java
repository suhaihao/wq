package com.bhst.wq.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhst.wq.entity.WqPeopleOrder;
import com.bhst.wq.entity.WqUser;
import com.bhst.wq.exception.BusinessInterfaceException;
import com.bhst.wq.request.WqPeopleOrderAddRequest;
import com.bhst.wq.request.WqPeopleOrderDetailDelRequest;
import com.bhst.wq.request.WqPeopleOrderPageListRequest;
import com.bhst.wq.response.WqPeopleOrderResponse;
import com.bhst.wq.service.WqPeopleOrderService;
import com.bhst.wq.service.WqUserService;
import com.bhst.wq.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/order")
@CrossOrigin
@Api("百姓点单接口")
public class WqPeopleOrderController {

    private final WqPeopleOrderService wqPeopleOrderService;
    private final WqUserService wqUserService;

    @Autowired
    public WqPeopleOrderController(WqPeopleOrderService wqPeopleOrderService, WqUserService wqUserService) {
        this.wqPeopleOrderService = wqPeopleOrderService;
        this.wqUserService = wqUserService;
    }

    @PostMapping("/getPageList")
    @ApiOperation(value = "获取百姓点单列表")
    public IPage<WqPeopleOrder> getPageList(@RequestBody WqPeopleOrderPageListRequest request) {
        return wqPeopleOrderService.getPageList(request);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加修改百姓点单")
    public Boolean add(@RequestBody WqPeopleOrderAddRequest request) {
        WqPeopleOrder wqPeopleOrder = new WqPeopleOrder();
        BeanUtils.copyProperties(request, wqPeopleOrder);
        wqPeopleOrder.setUpdateTime(LocalDateTime.now());
        if (null == wqPeopleOrder.getId()) {
            if (UserUtils.getUser() == null) {
                QueryWrapper<WqUser> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("phone", request.getPhone());
                WqUser one = wqUserService.getOne(queryWrapper);
                if (null == one) {
                    throw new BusinessInterfaceException("手机号不正确");
                }
                wqPeopleOrder.setCreateBy(String.valueOf(one.getId()));
            } else {
                wqPeopleOrder.setCreateBy(String.valueOf(UserUtils.getUserId()));
            }
        } else {
            WqPeopleOrder byId = wqPeopleOrderService.getById(wqPeopleOrder.getId());
            if (null != byId) {
                if (UserUtils.getUser() == null) {
                    QueryWrapper<WqUser> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("phone", request.getPhone());
                    WqUser one = wqUserService.getOne(queryWrapper);
                    wqPeopleOrder.setCreateBy(String.valueOf(one.getId()));
                } else {
                    wqPeopleOrder.setUserId(UserUtils.getUserId());
                }
            }
        }
        return wqPeopleOrderService.saveOrUpdate(wqPeopleOrder);
    }

    @PostMapping("/ExecuteOrder")
    @ApiOperation(value = "执行订单")
    public Boolean ExecuteOrder(@Valid @RequestBody WqPeopleOrderDetailDelRequest request) {
        WqPeopleOrderResponse byId = wqPeopleOrderService.getById(request);
        if (null != request.getIsPass() && request.getIsPass()) {
            byId.setIsAudit(1);
            WqPeopleOrder wqPeopleOrder = new WqPeopleOrder();
            BeanUtils.copyProperties(byId, wqPeopleOrder);
            return wqPeopleOrderService.saveOrUpdate(wqPeopleOrder);
        }
        return false;
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除百姓点单")
    public Boolean del(@Valid @RequestBody WqPeopleOrderDetailDelRequest request) {
        return wqPeopleOrderService.delById(request);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "获取百姓点单详情")
    public WqPeopleOrderResponse detail(@Valid @RequestBody WqPeopleOrderDetailDelRequest request) {
        return wqPeopleOrderService.getById(request);
    }

}
