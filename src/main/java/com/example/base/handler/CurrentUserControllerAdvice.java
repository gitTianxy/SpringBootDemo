package com.example.base.handler;

import com.example.base.bo.MyUserDetail;
import com.example.base.vo.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CurrentUserControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserControllerAdvice.class);

    /**
     * 为了给每个视图层(Model)塞一个currentUser(当前登录的user)的情况
     * @param authentication
     * @return
     */
    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : new CurrentUser((MyUserDetail) authentication.getPrincipal());
    }

}

