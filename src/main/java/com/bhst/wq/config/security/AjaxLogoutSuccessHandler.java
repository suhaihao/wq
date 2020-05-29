package com.bhst.wq.config.security;

import com.alibaba.druid.support.json.JSONUtils;
import com.bhst.wq.enumData.ExceptionEnum;
import com.bhst.wq.response.ResultBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResultBean responseBody = new ResultBean();
        responseBody.setCode(ExceptionEnum.SUCCESS.getCode());
        responseBody.setMsg(ExceptionEnum.SUCCESS.getMsg());
        //删除token信息
        httpServletResponse.getWriter().write(JSONUtils.toJSONString(responseBody));
    }
}
