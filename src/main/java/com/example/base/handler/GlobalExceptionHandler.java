package com.example.base.handler;

import com.example.base.util.JsonUtil;
import com.example.common.ErrorInfo;
import com.example.common.JsonException;
import com.example.common.ResultConstant;
import org.apache.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    public void writeUnauthorizedError(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.SC_UNAUTHORIZED);
        response.getWriter().write(JsonUtil.getJsonFromObject(ResultConstant.UNAUTHORIZED_RESULT));
        response.getWriter().close();
    }

    public void writeInternalServerError(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write(JsonUtil.getJsonFromObject(ResultConstant.SYS_ERR_RESULT));
        response.getWriter().close();
    }
}
