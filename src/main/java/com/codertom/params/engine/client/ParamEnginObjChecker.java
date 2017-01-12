package com.codertom.params.engine.client;

import com.codertom.params.engine.response.BaseBack;

/**
 * 对象校验参数
 */
public interface ParamEnginObjChecker<T> {

    /**
     * 对参数实体进行校验,参数实体需要实现get、Set方法
     * @return
     */
    public BaseBack checkParamObj(T t);

}
