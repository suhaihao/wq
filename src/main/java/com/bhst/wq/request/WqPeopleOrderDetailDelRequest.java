package com.bhst.wq.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("百姓点单详情请求体")
public class WqPeopleOrderDetailDelRequest {

    @NotNull(message = "id不能为空")
    @ApiModelProperty("唯一id")
    private Integer id;

    @ApiModelProperty("是否通过")
    private Boolean isPass;

}
