package com.codertom.params.engine.type;

import java.util.Map;

/**
 * 基本类型的接口
 */
public interface BasicType {
    /**
     * 参数校验，参数信息保存在map中
     * @param
     * @return
     */
     boolean checkParam(Map paramMap);

}
