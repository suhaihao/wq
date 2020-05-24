package com.bhst.wq.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("志愿风采添加修改请求体")
public class WqVolunteerStyleAddRequest {

    @ApiModelProperty("唯一id")
    private Integer id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("连接")
    private String link;

    @ApiModelProperty("图片")
    private String img;

    @ApiModelProperty("类型")
    private String type;


}
