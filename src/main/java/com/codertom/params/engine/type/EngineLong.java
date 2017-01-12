package com.codertom.params.engine.type;

import java.util.Map;

/**
 * Integer类型的引擎参数
 */
public class EngineLong implements BasicType{
    //数值
    private long number;
    //数值的类型
    private Class type = Long.class;
    //最大值上限
    private long max;
    //最小值下限
    private long min;
    //枚举范围
    private String range;
    //是否是必填字段
    private boolean ifnecessary;

    public void setNumber(long number) {
        this.number = number;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public void setIfnecessary(boolean ifnecessary) {
        this.ifnecessary = ifnecessary;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public long getNumber() {
        return number;
    }

    public Class getType() {
        return type;
    }

    public long getMax() {
        return max;
    }

    public long getMin() {
        return min;
    }

    public String getRange() {
        return range;
    }

    public boolean isIfnecessary() {
        return ifnecessary;
    }

    public boolean checkParam(Map paramMap) {
        return false;
    }
}
