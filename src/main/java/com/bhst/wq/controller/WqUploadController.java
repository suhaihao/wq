package com.bhst.wq.controller;

import com.bhst.wq.constants.Constants;
import com.bhst.wq.exception.BusinessInterfaceException;
import com.bhst.wq.response.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/upload")
@CrossOrigin
@Api("上传接口")
public class WqUploadController {


    @PostMapping("/file")
    @ApiOperation(value = "上传文件")
    public ResultBean<String> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
        log.info("开始上传");

        boolean isEmpty = file.isEmpty();
        if (isEmpty) {
            throw new BusinessInterfaceException("上传失败！上传的文件为空！");
        }

        long fileSize = file.getSize();
        log.info("文件大小:{}", fileSize);
        if (fileSize > Constants.fileSize) {
            throw new BusinessInterfaceException("上传失败！上传的文件大小超出了限制！");
        }

        String contentType = file.getContentType();
        log.info("文件类型:{}", contentType);
        List<String> types = new ArrayList<>();
        types.add("image/jpeg");
        types.add("image/png");
        types.add("image/gif");
        if (!types.contains(contentType)) {
            throw new BusinessInterfaceException("上传失败！不允许上传此类型的文件！");
        }
        String parentDir = request.getSession().getServletContext().getRealPath("upload");
        log.info("文件路径为:{}", parentDir);
        File parent = new File("/home/xucy/myfile/myjar/libin/app/wq/app/static/imgs/");
        if (!parent.exists()) {
            parent.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        String filename = UUID.randomUUID().toString();
        log.info("原文件名:{}修改为:{}", originalFilename, filename);
        String suffix = "";
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex != -1) {
            suffix = originalFilename.substring(beginIndex);
        }

        // 执行保存文件
        File dest = new File(parent, filename + suffix);
        file.transferTo(dest);
        log.info("上传结束");
        return new ResultBean<>(filename + suffix);
    }

}
