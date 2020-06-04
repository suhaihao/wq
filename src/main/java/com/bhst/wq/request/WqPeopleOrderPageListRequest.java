package com.bhst.wq.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("百姓点单列表请求体")
public class WqPeopleOrderPageListRequest {

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("是否查询当前用户点单")
    private Boolean isUser;

    @ApiModelProperty("是否查询当前用户拍单")
    private Boolean createBy;

    @ApiModelProperty("页数")
    private int pageIndex = 1;

    @ApiModelProperty("分页大小")
    private int pageSize = 20;

    @ApiModelProperty("是否需要审核")
    private Boolean isRank;
}
