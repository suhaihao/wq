package com.bhst.wq.response;

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
@ApiModel("队伍排行返回体")
public class WqTeamResponse {

    @ApiModelProperty("排行")
    private Integer rowNum;

    @ApiModelProperty("唯一id")
    private Integer id;

    @ApiModelProperty("管理者")
    private String manager;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("服务次数")
    private Integer numberOfServices;

    @ApiModelProperty("服务时长")
    private Integer serviceDuration;

    @ApiModelProperty("创建者")
    private String createBy;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("logo")
    private String teamLogo;

    @ApiModelProperty("团队名称")
    private String teamName;

    @ApiModelProperty("团队简介")
    private String teamIntro;

    @ApiModelProperty("队伍标语")
    private String teamSlogan;


}
