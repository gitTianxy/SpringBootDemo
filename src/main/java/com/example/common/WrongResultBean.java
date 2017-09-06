package com.example.common;

/**
 * Created by kevintian on 2017/9/6.
 */
public class WrongResultBean extends ResultBase {
    private String data;

    public WrongResultBean(Integer err, String data){
        this.err = err;
        this.data = data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
