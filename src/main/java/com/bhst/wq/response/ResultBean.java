package com.bhst.wq.response;

import com.bhst.wq.enumData.ExceptionEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class ResultBean<T> {

    @ApiParam(value = "状态码")
    public Integer code;
    @ApiParam(value = "信息")
    public String msg;
    @ApiParam(value = "业务数据")
    public T data;
    @ApiModelProperty(value = "服务器当前时间")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime time = LocalDateTime.now();

    public ResultBean() {
    }
    public ResultBean(T data) {
        this(ExceptionEnum.SUCCESS.getCode(), ExceptionEnum.SUCCESS.getMsg(), data);
    }

    public ResultBean(Integer code, String msg) {
        this(code, msg, null);
    }

    public ResultBean(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
