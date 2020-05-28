package com.bhst.wq.exception;

import com.bhst.wq.enumData.ExceptionEnum;
import com.bhst.wq.response.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class InterfaceExceptionHandler {

    /**
     * 接口 业务异常
     */
    @ResponseBody
    @ExceptionHandler(BusinessInterfaceException.class)
    public ResultBean businessInterfaceException(BusinessInterfaceException e) {
        log.error(e.getMsg(), e);
        return new ResultBean(e.getCode(), e.getMsg());
    }

    /**
     * 拦截所有运行时的全局异常   
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultBean runtimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ResultBean(ExceptionEnum.RUNTIMEEXCEPTION.getCode(), ExceptionEnum.RUNTIMEEXCEPTION.getMsg());
    }

    /**
     * 系统异常捕获处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultBean exception(Exception e) {
        log.error(e.getMessage(), e);
        return new ResultBean(ExceptionEnum.RUNTIMEEXCEPTION.getCode(), ExceptionEnum.RUNTIMEEXCEPTION.getMsg());
    }
}
