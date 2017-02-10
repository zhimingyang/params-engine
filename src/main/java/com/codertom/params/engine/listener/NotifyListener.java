package com.codertom.params.engine.listener;

import java.util.List;

/**
 * 状态变化的监听器
 */
public interface NotifyListener {

    /**
     *
     * @param urls
     */
    void notify(List<String> urls);



}
