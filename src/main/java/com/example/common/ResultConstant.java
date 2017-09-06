package com.example.common;

import org.apache.http.HttpStatus;

/**
 * Created by kevintian on 2017/9/6.
 */
public class ResultConstant {
    public static final Integer SUCC = 0;

    public static final RightResultBean RIGHT_RESULT = new RightResultBean();

    public static final WrongResultBean SYS_ERR_RESULT = new WrongResultBean(HttpStatus.SC_INTERNAL_SERVER_ERROR, "server internal error");
    public static final WrongResultBean NOT_FOUND_RESULT = new WrongResultBean(HttpStatus.SC_NOT_FOUND, "not found");
    public static final WrongResultBean UNPROCESSABLE_RESULT = new WrongResultBean(HttpStatus.SC_UNPROCESSABLE_ENTITY, "unprocessable entity");
    public static final WrongResultBean UNAUTHORIZED_RESULT = new WrongResultBean(HttpStatus.SC_UNAUTHORIZED, "unauthorized");
}
