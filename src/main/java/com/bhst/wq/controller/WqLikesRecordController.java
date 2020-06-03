package com.bhst.wq.controller;

import com.bhst.wq.request.WqLikesRecordQueryRequest;
import com.bhst.wq.response.ResultBean;
import com.bhst.wq.service.WqLikesRecordService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/like")
@CrossOrigin
@Api("点赞信息")
public class WqLikesRecordController {

    private final WqLikesRecordService wqLikesRecordService;

    @Autowired
    public WqLikesRecordController(WqLikesRecordService wqLikesRecordService) {
        this.wqLikesRecordService = wqLikesRecordService;
    }

    @PostMapping("/getUserLike")
    public ResultBean<Boolean> getUserLike(@Valid @RequestBody WqLikesRecordQueryRequest request) {
        return new ResultBean(wqLikesRecordService.getByUserAndType(request));
    }
}
