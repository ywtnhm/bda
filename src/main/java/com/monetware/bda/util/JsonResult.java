package com.monetware.bda.util;

import java.util.HashMap;
import java.util.Map;

public class JsonResult {

    private String  code;

    private String msg;

    private Object data;

    private Map<String, Object> extras;

    public JsonResult(String  code) {
        this( code, "");
    }

    public JsonResult(String  code, Object data) {
        this( code, "", data);
    }

    public JsonResult(String  code, String msg) {
        this( code, msg, null);
    }

    public JsonResult(String  code, String msg, Object data) {
        this. code =  code;
        this.msg = msg;
        this.data = data;
    }

    public JsonResult(String  code, String msg, Object data, Map<String, Object> extras) {
        this. code =  code;
        this.msg = msg;
        this.data = data;
        this.extras = extras;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setExtras(Map<String, Object> extras) {
        this.extras = extras;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }

    public void addExtra(String key, Object value) {
        if (this.extras == null) {
            this.extras = new HashMap<String, Object>();
        }
        this.extras.put(key, value);
    }
}
