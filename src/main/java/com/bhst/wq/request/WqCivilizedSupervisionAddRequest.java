package com.bhst.wq.request;


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
@ApiModel("文明监督添加修改请求体")
public class WqCivilizedSupervisionAddRequest {

    @ApiModelProperty("唯一id")
    private Integer id;

    @ApiModelProperty("图片")
    private String img;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("链接")
    private String link;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("讨厌数")
    private Integer dislikes;


}
