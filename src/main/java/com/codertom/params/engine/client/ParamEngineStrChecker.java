package com.codertom.params.engine.client;

import com.codertom.params.engine.response.BaseBack;

/**
 * String模式的参数校验
 */
public interface ParamEngineStrChecker {
    /**
     * 对String类型的参数进行校验
     * @return
     */
    public BaseBack checkParamStr(String param);

}
