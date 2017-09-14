package com.example.web;

import com.example.base.domain.User;
import com.example.base.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by kevintian on 2017/9/14.
 */
@Controller
public class LogoutController {
    Logger logger  = LoggerFactory.getLogger(LogoutController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/page/logout", method = RequestMethod.GET)
    public String logout(Authentication auth, Model model) {
        logger.info("current user: {}", auth.getName());
        User u = userService.getUserByName(auth.getName());
        model.addAttribute("currentUser", u);
        return "logout";
    }
}
