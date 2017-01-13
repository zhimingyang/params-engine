package com.codertom.params.engine.chain;


import java.util.Map;

/**
 * 参数校验的责任链
 */
public interface ParamCheckChain {
    /**
     * 参数校验
     * @param info
     * @return
     */
    Map doCheck(Map info);

}
