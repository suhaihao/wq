package com.bhst.wq.exception;

import com.bhst.wq.enumData.ExceptionEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessInterfaceException extends RuntimeException {

    private Integer code = ExceptionEnum.BUSINESS.getCode();
    private String msg = ExceptionEnum.BUSINESS.getMsg();

    public BusinessInterfaceException() {
    }

    public BusinessInterfaceException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessInterfaceException(String msg) {
        this.msg = msg;
    }
}
