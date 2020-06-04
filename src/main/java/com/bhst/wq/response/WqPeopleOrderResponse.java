package com.bhst.wq.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel("百姓点单详情返回体")
public class WqPeopleOrderResponse {

    @ApiModelProperty("唯一id")
    private Integer id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("类型")
    private String type;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("创建者")
    private String createBy;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("修改者")
    private String updateBy;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("是否审核0没有1有")
    private Integer isAudit;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("用户名称")
    private String fullname;

    @ApiModelProperty("团队名称")
    private String teamName;
}
