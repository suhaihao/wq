package com.bhst.wq.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("活动添加修改请求体")
public class WqActivityRecruitmentAddRequest {

    @ApiModelProperty("唯一id")
    private Integer id;
    
    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("参与人数")
    private Integer participateNum;

    @ApiModelProperty("总人数")
    private Integer totalNum;

    @ApiModelProperty("联系人")
    private String contacts;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("活动内容")
    private String content;

    @ApiModelProperty("活动图片地址")
    private String imgs;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty("活动类型 1 志愿活动 2 活动通知 3 活动公告 -1 其他")
    @TableField(value = "type")
    private Integer type;

    @ApiModelProperty("活动状态 0招募中 1 进行中 -1已结束")
    @TableField(value = "status")
    private Integer status;


}
