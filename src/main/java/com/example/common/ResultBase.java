package com.example.common;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
public abstract class ResultBase {

    protected Integer err;


    public Integer getErr() {
        return err;
    }

    public void setErr(Integer err) {
        this.err = err;
    }


}
