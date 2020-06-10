package com.bhst.wq.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("活动列表请求体")
public class WqActivityRecruitmentPageListRequest {

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("页数")
    private int pageIndex = 1;

    @ApiModelProperty("分页大小")
    private int pageSize = 20;

    @ApiModelProperty("是否显示级别")
    private Boolean isRank;

    @ApiModelProperty("是否当前用户招募活动")
    private Boolean isUser;

}
