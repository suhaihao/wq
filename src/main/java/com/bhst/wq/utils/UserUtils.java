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

    public static Integer getUserLeve() {
        WqUser user = (WqUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (null != user) {
            return user.getLevel();
        }
        return null;
    }

    public static WqUser getUser() {
        WqUser user = (WqUser) SecurityContextHolder.getContext().getAuthentication();
        if (null != user) {
            return user;
        }
        return null;
    }
}
