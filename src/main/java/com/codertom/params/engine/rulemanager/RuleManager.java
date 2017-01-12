package com.codertom.params.engine.rulemanager;

import com.codertom.params.engine.event.EngineEvent;

/**
 * 规则管理器
 */
public interface RuleManager {
    /**
     * 管理器初始化
     */
    void init();

    /**
     * 参数加载
     * @param event
     */
    void load(EngineEvent event);

    /**
     * 参数删除
     * @param event
     */
    void delete(EngineEvent event);
}
