package com.bhst.wq.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("文明课堂添加修改请求体")
public class WqCivilizedClassroomAddRequest {

    @ApiModelProperty("唯一id")
    private Integer id;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("连接")
    private String link;

    @ApiModelProperty("主图")
    private String imgs;

    @ApiModelProperty("likes")
    private String likes;

}
