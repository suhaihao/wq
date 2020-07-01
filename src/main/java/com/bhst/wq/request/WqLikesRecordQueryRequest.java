package com.bhst.wq.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("根据类型查询用户是否点赞请求体")
public class WqLikesRecordQueryRequest {

    @NotNull(message = "类型不能为空")
    @ApiModelProperty("类型")
    private Integer type;

    @NotNull(message = "类型id不能为空")
    @ApiModelProperty("类型id")
    private Integer typeId;


}
