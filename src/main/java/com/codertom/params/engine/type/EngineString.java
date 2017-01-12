package com.codertom.params.engine.type;

import java.util.Map;

/**
 * 引擎字符串
 */
public class EngineString implements BasicType{

    //字符串的内容
    private String strContant;
    //字符串的类型
    private Class type = String.class;
    //正则表达式
    private String reg;
    //是否可为空
    private boolean isEmpty;
    //是否可以为Null
    private boolean isNull;
    //是否为必要字段
    private boolean ifNecessary;

    public String getStrContant() {
        return strContant;
    }

    public Class getType() {
        return type;
    }

    public String getReg() {
        return reg;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isNull() {
        return isNull;
    }

    public boolean isIfNecessary() {
        return ifNecessary;
    }

    public void setStrContant(String strContant) {
        this.strContant = strContant;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public void setNull(boolean aNull) {
        isNull = aNull;
    }

    public void setIfNecessary(boolean ifNecessary) {
        this.ifNecessary = ifNecessary;
    }

    public boolean checkParam(Map paramMap) {
        return false;
    }
}
