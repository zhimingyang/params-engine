package com.codertom.params.engine.chain;

import java.util.Map;

import com.codertom.params.engine.type.BasicType;

/**
 * 快速失败的责任链，一个参数校验失败即返回
 */
public class FailOverParamCheckChain implements ParamCheckChain{

    private BasicType basicType;

    private ParamCheckChain nextChain;

    public BasicType getBasicType() {
        return basicType;
    }

    public ParamCheckChain getNextChain() {
        return nextChain;
    }

    public void setBasicType(BasicType basicType) {
        this.basicType = basicType;
    }

    public void setNextChain(ParamCheckChain nextChain) {
        this.nextChain = nextChain;
    }

    public FailOverParamCheckChain(BasicType type){
        this.basicType = type;
    }

    public Map doCheck(Map info){
        boolean nextFlag = basicType.checkParam(info);
        if(nextFlag)
            return this.nextChain.doCheck(info);
        else
            return info;
    }

}
