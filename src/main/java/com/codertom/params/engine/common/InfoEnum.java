package com.codertom.params.engine.common;

/**
 * 返回信息的详细程度
 */
public enum InfoEnum {
    //最详细
    DEBUG(1),
    //部分详细
    INFO(2),
    //没有
    NO(3);

    private int level;

    InfoEnum(int InfoEnum){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }
}
