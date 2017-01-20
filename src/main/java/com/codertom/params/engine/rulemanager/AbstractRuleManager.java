package com.codertom.params.engine.rulemanager;

import com.codertom.params.engine.chain.ParamCheckChain;
import com.codertom.params.engine.common.Constants;
import com.codertom.params.engine.common.LocalConf;
import com.codertom.params.engine.common.Path;
import com.codertom.params.engine.event.EngineEvent;
import com.codertom.params.engine.registry.EngineRegistry;
import com.codertom.params.engine.utils.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ruleManager的抽象类
 */
public class AbstractRuleManager implements RuleManager {
    // 引擎配置文件解析器
    private RuleParser parser;
    //引擎的注册中心
    private EngineRegistry registry;
    //本地参数配置
    private LocalConf conf;

    //参数到校验规则链的映射
    private ConcurrentHashMap<String, ParamCheckChain> paramMap;

    private static Logger logger = LoggerFactory.getLogger(AbstractRuleManager.class);

    public AbstractRuleManager(){
        parser = new RuleParser();
        paramMap = new ConcurrentHashMap<String, ParamCheckChain>(100);

    }


    @Override
    public void init() {
        String engineConfFilePath = ConfigUtil.getProperty(Constants.ENGINE_PATH);
        List<Path> pashs = null;
    }

    public void load(EngineEvent event) {

    }

    public void delete(EngineEvent event) {

    }

    public void loadParamConfigs() {

    }

    /**
     * 本地配置文件管理器
     */
    public class LocalConfManager{
        //本地Cache缓存
        private LocalConf conf;

        public LocalConfManager(String paramConfLocation){

        }


    }
}



