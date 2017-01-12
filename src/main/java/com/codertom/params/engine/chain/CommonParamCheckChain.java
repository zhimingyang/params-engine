package com.codertom.params.engine.chain;

import com.codertom.params.engine.type.BasicType;

import java.util.Map;

/**
 * 通用的参数检查链，检查所有参数
 */
public class CommonParamCheckChain {

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

    public CommonParamCheckChain(BasicType type){
        this.basicType = type;
    }

    public Map doCheck(Map info){
        basicType.checkParam(info);
        return this.nextChain.doCheck(info);
    }

}
