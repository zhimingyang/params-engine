package com.codertom.params.engine.rulemanager;

import com.codertom.params.engine.chain.ParamCheckChain;
import com.codertom.params.engine.common.Constants;
import com.codertom.params.engine.event.EngineEvent;
import com.codertom.params.engine.utils.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ruleManager的抽象类
 */
public class AbstractRuleManager implements RuleManager {

    private RuleParser parser;

    private Lock mainLock = new ReentrantLock();
    //参数到校验规则链的映射
    private ConcurrentHashMap<String, ParamCheckChain> paramMap;

    private static Logger logger = LoggerFactory.getLogger(AbstractRuleManager.class);

    public void init() {
        parser = new RuleParser();
        paramMap = new ConcurrentHashMap<String, ParamCheckChain>(100);
        String engineConfFilePath = ConfigUtil.getProperty(Constants.ENGINE_PATH);

    }

    public void load(EngineEvent event) {

    }

    public void delete(EngineEvent event) {

    }

    public void loadParamConfigs() {

    }




}
