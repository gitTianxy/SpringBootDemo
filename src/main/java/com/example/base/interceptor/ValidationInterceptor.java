package com.example.base.interceptor;

import com.example.base.handler.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kevintian on 2017/9/6.
 */
@Component
public class ValidationInterceptor implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(ValidationInterceptor.class);

    @Autowired
    GlobalExceptionHandler exceptionHandler;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        if (false) {
            exceptionHandler.writeUnauthorizedError(response);
            logger.info("validation before method--FAIL. request: {}", request.getRequestURL());
            return false;
        } else {
            logger.info("validation before method--SUCC.");
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {
        logger.info("validation after method...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {
        logger.info("Actions after validation...");
    }

}
