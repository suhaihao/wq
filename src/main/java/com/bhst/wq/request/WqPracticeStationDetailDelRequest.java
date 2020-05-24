package com.bhst.wq.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("实践阵地详情请求体")
public class WqPracticeStationDetailDelRequest {
    @NotNull
    @ApiModelProperty("唯一id")
    private Integer id;
}
