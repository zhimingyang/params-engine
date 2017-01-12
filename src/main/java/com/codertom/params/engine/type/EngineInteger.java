package com.codertom.params.engine.type;

import java.util.Map;

/**
 * Integer类型的引擎参数
 */
public class EngineInteger implements BasicType{
    //数值
    private int number;
    //数值的类型
    private Class type = Integer.class;
    //最大值上限
    private int max;
    //最小值下限
    private int min;
    //枚举范围
    private String range;
    //是否是必填字段
    private boolean ifnecessary;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setIfnecessary(boolean ifnecessary) {
        this.ifnecessary = ifnecessary;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public int getNumber() {
        return number;
    }

    public Class getType() {
        return type;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public boolean isIfnecessary() {
        return ifnecessary;
    }

    public String getRange() {
        return range;
    }

    public boolean checkParam(Map paramMap) {
        return false;
    }
}
