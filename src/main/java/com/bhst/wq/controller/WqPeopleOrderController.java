package com.bhst.wq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhst.wq.entity.WqPeopleOrder;
import com.bhst.wq.request.WqPeopleOrderAddRequest;
import com.bhst.wq.request.WqPeopleOrderDetailDelRequest;
import com.bhst.wq.request.WqPeopleOrderPageListRequest;
import com.bhst.wq.service.WqPeopleOrderService;
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

    @Autowired
    public WqPeopleOrderController(WqPeopleOrderService wqPeopleOrderService) {
        this.wqPeopleOrderService = wqPeopleOrderService;
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
            wqPeopleOrder.setCreateBy(String.valueOf(UserUtils.getUserId()));
        } else {
            WqPeopleOrder byId = wqPeopleOrderService.getById(wqPeopleOrder.getId());
            if (null == byId) {
                wqPeopleOrder.setUserId(UserUtils.getUserId());
            }
        }
        return wqPeopleOrderService.saveOrUpdate(wqPeopleOrder);
    }

    @PostMapping("/ExecuteOrder")
    @ApiOperation(value = "执行订单")
    public Boolean ExecuteOrder(@Valid @RequestBody WqPeopleOrderDetailDelRequest request) {
        WqPeopleOrder byId = wqPeopleOrderService.getById(request);
        if (null != request.getIsPass() && request.getIsPass()) {
            byId.setIsAudit(1);
            return wqPeopleOrderService.saveOrUpdate(byId);
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
    public WqPeopleOrder detail(@Valid @RequestBody WqPeopleOrderDetailDelRequest request) {
        return wqPeopleOrderService.getById(request);
    }

}
