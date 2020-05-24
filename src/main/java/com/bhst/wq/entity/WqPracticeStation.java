package com.bhst.wq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@ApiModel("实践阵地")
@TableName("wq_practice_station")
public class WqPracticeStation {

    @ApiModelProperty("唯一id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("名称")
    @TableField(value = "station_name")
    private String stationName;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("地址")
    @TableField(value = "address")
    private String address;

    @ApiModelProperty("管理者")
    @TableField(value = "manager")
    private String manager;

    @ApiModelProperty("手机号")
    @TableField(value = "phone")
    private String phone;

    @ApiModelProperty("简介")
    @TableField(value = "intro")
    private String intro;

    @ApiModelProperty("图片")
    @TableField(value = "imgs")
    private String imgs;

    @ApiModelProperty("中心id")
    @TableField(value = "center_id")
    private Integer centerId;

    @ApiModelProperty("所id")
    @TableField(value = "institute_id")
    private Integer instituteId;


}
