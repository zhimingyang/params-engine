package com.codertom.params.engine.common;

/**
 * EnginEnvent的枚举
 */
public enum ActionEnum {
    //增加
    ADD(1),
    //更新
    UPDATE(2),
    //删除
    DELETE(3);

    private int action;

    ActionEnum(int action){
        this.action = action;
    }

    public int getAction(){
        return action;
    }

}
