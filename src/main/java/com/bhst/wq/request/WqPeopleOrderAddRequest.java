package com.bhst.wq.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("百姓点单添加修改请求体")
public class WqPeopleOrderAddRequest {

    @ApiModelProperty("唯一id")
    private Integer id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("内容")
    private String content;


}
