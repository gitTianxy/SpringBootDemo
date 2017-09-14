package com.example.web;

import com.example.base.domain.User;
import com.example.base.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * Created by kevintian on 2017/9/6.
 */
@Controller
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@CookieValue(value = "name", defaultValue = "default") String nameCk, HttpServletRequest request, HttpServletResponse response) {
        logger.info("***post login");
        String name = request.getParameter("name");
        String passwd = request.getParameter("passwd");
        if (!nameCk.equals("default") && nameCk.equals(name)) {
            return new ResponseEntity("already login", HttpStatus.OK);
        }
        User u = userService.getUserByName(name);
        if (u == null) {
            return new ResponseEntity(String.format("login fail. user(%s,%s) does not exist", name, passwd),
                    HttpStatus.NOT_FOUND);
        }
        if (!u.getPasswd().equals(passwd)) {
            return new ResponseEntity("login fail. name or passwd error", HttpStatus.BAD_REQUEST);
        }
        Cookie cookie = new Cookie("name", name);
        cookie.setMaxAge(1000); // expire in 1000 secs;
        response.addCookie(cookie);
        return new ResponseEntity("login succ.", HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        logger.debug("Getting login page, error={}", error);
        return new ModelAndView("login", "error", error);
    }
}
