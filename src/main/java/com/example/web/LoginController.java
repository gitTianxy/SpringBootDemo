package com.example.web;

import com.example.domain.User;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by kevintian on 2017/9/6.
 */
@RestController
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity login(@CookieValue(value = "name", defaultValue = "default") String nameCk, @RequestParam
            ("name") String name, @RequestParam("passwd") String passwd, HttpServletResponse response) {
        if (!nameCk.equals("default") && nameCk.equals(name)) {
            return new ResponseEntity("already login", HttpStatus.OK);
        }
        List<User> users = userService.getUserByName(name);
        if (users.isEmpty()) {
            return new ResponseEntity(String.format("login fail. user(%s,%s) does not exist", name, passwd),
                    HttpStatus.NOT_FOUND);
        }
        User u = users.get(0);
        if (!u.getPasswd().equals(passwd)) {
            return new ResponseEntity("login fail. name or passwd error", HttpStatus.BAD_REQUEST);
        }
        Cookie cookie = new Cookie("name", name);
        cookie.setMaxAge(1000); // expire in 1000 secs;
        response.addCookie(cookie);
        return new ResponseEntity("login succ.", HttpStatus.OK);
    }
}
