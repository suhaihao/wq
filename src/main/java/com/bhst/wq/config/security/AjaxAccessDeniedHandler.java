package com.bhst.wq.config.security;

import com.alibaba.druid.support.json.JSONUtils;
import com.bhst.wq.enumData.ExceptionEnum;
import com.bhst.wq.response.ResultBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResultBean responseBody = new ResultBean();
        responseBody.setCode(ExceptionEnum.NORIGHTS.getCode());
        responseBody.setMsg(ExceptionEnum.NORIGHTS.getMsg());
        httpServletResponse.getWriter().write(JSONUtils.toJSONString(responseBody));
    }
}
