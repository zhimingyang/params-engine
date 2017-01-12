package com.codertom.params.engine.registry;

import com.codertom.params.engine.event.EngineEvent;

/**
 * 注册服务
 */
public interface EngineRegistry {

    void register(EngineEvent event);

    void unregister(EngineEvent event);


}
