package com.example.web;

import com.example.common.JsonException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {

    @RequestMapping(value = "/error/default", method = RequestMethod.GET)
    public void defaultError() throws Exception {
        throw new Exception("default error");
    }

    @RequestMapping(value = "/error/json", method = {RequestMethod.GET, RequestMethod.POST})
    public void jsonError() throws Exception {
        throw new JsonException("json error");
    }
}
