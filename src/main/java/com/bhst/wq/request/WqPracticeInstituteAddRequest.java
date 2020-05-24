package com.bhst.wq.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("实践所添加修改请求体")
public class WqPracticeInstituteAddRequest {

    @ApiModelProperty("唯一id")
    private Integer id;

    @ApiModelProperty("名称")
    private String instituteName;


    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("管理者")
    private String manager;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("简介")
    private String intro;

    @ApiModelProperty("图片")
    private String imgs;


}
