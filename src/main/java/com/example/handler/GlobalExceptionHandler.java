package com.example.handler;

import com.example.common.ErrorInfo;
import com.example.common.JsonException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        request.setAttribute("url", request.getRequestURL());
        request.setAttribute("exception", e);
        return "/error";
    }

    @ExceptionHandler(value = JsonException.class)
    public @ResponseBody
    ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, JsonException e) throws Exception {
        ErrorInfo<String> error = new ErrorInfo<>();
        error.setMessage(e.getMessage());
        error.setCode(ErrorInfo.ERROR);
        error.setData("Some Data");
        error.setUrl(req.getRequestURL().toString());
        return error;
    }
}
