package com.bhst.wq.response;

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
@ApiModel("用户排行返回体")
public class WqUserResponse {

    @ApiModelProperty("排行")
    private Integer rowNum;

    @ApiModelProperty("唯一id")
    private Integer id;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("姓名")
    private String fullname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("身份证")
    private Integer idNumber;

    @ApiModelProperty("微信")
    private String chat;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("生日")
    private LocalDateTime bornDate;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("等级")
    private String grade;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("头像图片")
    private String headImg;

    @ApiModelProperty("创建者")
    private String createBy;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("修改者")
    private Integer updateBy;

    @ApiModelProperty("积分")
    private Integer integral;

    @ApiModelProperty("队伍")
    private String team;

    @ApiModelProperty("政治面貌")
    private String politicalOutlook;

    @ApiModelProperty("工作单位")
    private String workUnit;

    @ApiModelProperty("职业")
    private String occupation;

    @ApiModelProperty("学历")
    private String highestEducation;

    @ApiModelProperty("是否培训过")
    private Integer isTrain;

    @ApiModelProperty("技能")
    private String serviceSkills;

    @ApiModelProperty("民族")
    private String nation;

    @ApiModelProperty("服务类型")
    private String servicType;


}
