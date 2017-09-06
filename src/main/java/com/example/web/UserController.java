package com.example.web;

import com.example.common.JsonException;
import com.example.domain.User;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String getUsers(String name, Model model) throws Exception {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.POST)
    public ResponseEntity post(@PathVariable("name") String name, @RequestParam("age") Integer age, @RequestParam("passwd") String passwd) {
        List<User> users = userService.getUserByName(name);
        if (users.isEmpty()) {
            User newUser = userService.create(name, age, passwd);
            return new ResponseEntity(newUser, HttpStatus.OK);
        } else {
            int count = 0;
            for(User u: users) {
                u.setName(name);
                u.setAge(Long.valueOf(age));
                u.setPasswd(passwd);
                userService.update(u);
                count++;
            }
            return new ResponseEntity(String.format("%s users updated", count), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public @ResponseBody User put(HttpServletRequest request) {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String passwd = request.getParameter("passwd");
        return userService.create(name, Integer.valueOf(age), passwd);
    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("name") String name) {
        int count = userService.deleteByName(name);
        String msg = String.format("%s users deleted", count);
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }
}
