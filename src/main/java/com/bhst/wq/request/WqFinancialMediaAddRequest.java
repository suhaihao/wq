package com.bhst.wq.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("溶媒武强添加修改请求体")
public class WqFinancialMediaAddRequest {

    @ApiModelProperty("唯一id")
    private Integer id;

    @ApiModelProperty("溶媒体名称")
    private String mediaName;

    @ApiModelProperty("连接")
    private String mediaLink;

    @ApiModelProperty("logo")
    private String mediaLogo;

    @ApiModelProperty("资源")
    private String mediaSource;

    @ApiModelProperty("类型")
    private String type;

}
