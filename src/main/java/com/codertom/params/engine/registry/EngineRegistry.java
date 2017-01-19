package com.codertom.params.engine.registry;

import com.codertom.params.engine.common.URL;
import com.codertom.params.engine.listener.NotifyListener;

/**
 * 注册服务
 */
public interface EngineRegistry {

    /**
     * 引擎服务注册
     * @param path
     */
    void register(URL path);

    /**
     * 引擎服务取消注册
     * @param path
     */
    void unregister(URL path);

    /**
     * 服务订阅
     * @param path
     * @param listener
     */
    void subscribe(URL path, NotifyListener listener);

    /**
     * 服务取消注册
     * @param path
     * @param listener
     */
    void unsubscribe(URL path,NotifyListener listener);
}
