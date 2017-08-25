package com.example.utils;

import java.nio.charset.Charset;

public class StringUtil {
    /**
     * convert bytes to string
     * @param bytes
     * @param encoding
     * @return
     */
    public static String bytes2String(byte[] bytes, String encoding) {
        return new String(bytes, Charset.forName(encoding));
    }
}
