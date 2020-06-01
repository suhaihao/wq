package com.bhst.wq.utils;


import com.bhst.wq.entity.WqUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {

    public static Integer getUserId() {
        WqUser user = (WqUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (null != user) {
            return user.getId();
        }
        return null;
    }
}
