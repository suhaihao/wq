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
@ApiModel("溶媒武强")
@TableName("wq_financial_media")
public class WqFinancialMedia {
    @ApiModelProperty("唯一id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("溶媒体名称")
    @TableField(value = "media_name")
    private String mediaName;

    @ApiModelProperty("连接")
    @TableField(value = "media_link")
    private String mediaLink;

    @ApiModelProperty("logo")
    @TableField(value = "media_logo")
    private String mediaLogo;

    @ApiModelProperty("资源")
    @TableField(value = "media_source")
    private String mediaSource;

    @ApiModelProperty("类型")
    @TableField(value = "type")
    private String type;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("创建者")
    @TableField(value = "create_by")
    private String createBy;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新时间")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("修改者")
    @TableField(value = "update_by")
    private String updateBy;


}
