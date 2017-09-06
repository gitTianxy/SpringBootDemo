package com.example.common;

import org.springframework.core.NestedRuntimeException;

/**
 * Created by kevintian on 2017/9/6.
 */
public class ConvertException extends NestedRuntimeException {

    public ConvertException(String msg) {
        super(msg);
    }

    public ConvertException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
