package com.codertom.params.engine.rulemanager;

import com.codertom.params.engine.chain.ParamCheckChain;
import com.codertom.params.engine.event.EngineEvent;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ruleManager的抽象类
 */
public class AbstractRulrManager implements RuleManager{
    //参数到校验规则链的映射
    private ConcurrentHashMap<String,ParamCheckChain> paramMap;

    public void init() {
        paramMap = new ConcurrentHashMap<String, ParamCheckChain>(100);

    }

    public void load(EngineEvent event) {

    }

    public void delete(EngineEvent event) {

    }

    public void loadParamConfigs(){

    }


}
