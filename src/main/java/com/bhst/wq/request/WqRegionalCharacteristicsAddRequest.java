package com.bhst.wq.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("风俗添加修改请求体")
public class WqRegionalCharacteristicsAddRequest {

    @ApiModelProperty("唯一id")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("图片")
    private String imgs;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("连接")
    private String link;

}
