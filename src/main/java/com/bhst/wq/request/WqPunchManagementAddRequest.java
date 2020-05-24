package com.bhst.wq.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("打卡更新添加请求体")
public class WqPunchManagementAddRequest {

    @ApiModelProperty("唯一id")
    private Integer id;

    @ApiModelProperty("活动id")
    private Integer activityId;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("打卡二维码信息")
    private String punchCode;


}
