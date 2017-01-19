package com.codertom.params.engine.registry;

import com.codertom.params.engine.utils.ZookeeperClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Zookeeper实现的发布订阅
 */
public class ZookeeperEngineRegistry extends AbStractEngineRegistry{

    // 日志输出
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private ZookeeperClient client;
    //默认的Zk的root路径
    private String DEFAULT_ROOT = "params-engine";
    //默认的端口
    private final static int DEFAULT_ZOOKEEPER_PORT = 2181;

    public ZookeeperEngineRegistry(){

    }





}
