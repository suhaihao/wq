package com.bhst.wq.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("实践阵地列表请求体")
public class WqPracticeStationPageListRequest {

    @ApiModelProperty("所id")
    private Integer instituteId;

    @ApiModelProperty("页数")
    private int pageIndex = 1;

    @ApiModelProperty("分页大小")
    private int pageSize = 20;
}
