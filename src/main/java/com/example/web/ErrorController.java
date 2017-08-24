package com.example.web;

import com.example.common.JsonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {
    private final static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping(value = "/error/default", method = RequestMethod.GET)
    public void defaultError() throws Exception {
        Exception error = new Exception("default error");
        logger.error("", error);
        throw error;
    }

    @RequestMapping(value = "/error/json", method = {RequestMethod.GET, RequestMethod.POST})
    public void jsonError() throws Exception {
        Exception error = new JsonException("json error");
        logger.error("", error);
        throw error;
    }
}
