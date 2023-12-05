package com.chason.words.common;

import lombok.Data;

@Data
public class R {

    private Integer code;

    private Object data;

    private String message;

    public static R ok() {
        R r = new R();
        r.setCode(0);
        r.setMessage("执行成功！");
        return r;
    }

    public static R error () {
        R r = new R();
        r.setCode(-1);
        r.setMessage("执行失败！");
        return r;
    }

    public static R error(Integer code, String message) {
        R r = new R();
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public static R ok(Object data) {
        R r = ok();
        r.setData(data);
        return r;
    }

}
