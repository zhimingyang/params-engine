package com.codertom.params.engine.response;

import com.alibaba.fastjson.JSONObject;

/**
 * 返回对象实体
 */
public class BaseBack {
    //返回状态码
    public int code;
    //返回信息
    public String msg;
    //返回数据实体
    public JSONObject data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public JSONObject getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
