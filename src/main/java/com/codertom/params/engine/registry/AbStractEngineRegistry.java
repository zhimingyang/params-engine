package com.codertom.params.engine.registry;

import com.codertom.params.engine.common.ConcurrentHashSet;
import com.codertom.params.engine.common.Path;
import com.codertom.params.engine.common.URL;
import com.codertom.params.engine.listener.NotifyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 抽象注册中心类，主要做公共逻辑的抽象
 */
public abstract class AbStractEngineRegistry implements EngineRegistry {

    // 日志输出
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    //保存所有的注册的,URL
    private final Set<URL> registered = new ConcurrentHashSet<URL>();

    private final ConcurrentMap<URL, Map<String, List<URL>>> notified = new ConcurrentHashMap<URL, Map<String, List<URL>>>();

    //保存所有订阅URL，url对应的NotifyListener集合
    private final ConcurrentMap<URL, Set<NotifyListener>> subscribed = new ConcurrentHashMap<URL, Set<NotifyListener>>();

    //保存URL到Path的映射，在构造的时候进行初始化
    private final ConcurrentHashMap<URL,Path> PramPaths = new ConcurrentHashMap<URL,Path>();

    public AbStractEngineRegistry(){

    }

    /**
     * 根据pathName 获取对应的xml的Path对象
     * @param paramName
     * @return
     */
    public Path getPathByName(String paramName){
        URL url = new URL();
        url.setPath(paramName);
        return this.doGetPathByName(url);
    }

    /**
     * 抽象接口，子类具体场景进行实现
     * @param url
     * @return
     */
    public Path doGetPathByName(URL url){
        return null;
    };

    /**
     * 获取当前缓存的所有的xml文件的实现
     * @return
     */
    public List<Path> getAllPaths(){
        return this.doGetAllPaths();
    }

    /**
     * 抽象接口，子类具体场景进行实现
     * @return
     */
    public List<Path> doGetAllPaths(){
        return null;
    }

    @Override
    public void register(URL url) {
        if (url == null) {
            throw new IllegalArgumentException("register url == null");
        }
        if (logger.isInfoEnabled()){
            logger.info("Register: " + url);
        }
        registered.add(url);
    }

    void doRegiste(URL url){}

    @Override
    public void unregister(URL url) {
        if (url == null) {
            throw new IllegalArgumentException("unregister url == null");
        }
        if (logger.isInfoEnabled()){
            logger.info("Unregister: " + url);
        }
        registered.remove(url);
    }

    void doUnRegiste(URL url){}

    @Override
    public void subscribe(URL url, NotifyListener listener) {

        if (url == null) {
            throw new IllegalArgumentException("subscribe url == null");
        }
        if (listener == null) {
            throw new IllegalArgumentException("subscribe listener == null");
        }
        if (logger.isInfoEnabled()){
            logger.info("Subscribe: " + url);
        }
        Set<NotifyListener> listeners = subscribed.get(url);
        if (listeners == null) {
            subscribed.putIfAbsent(url, new ConcurrentHashSet<NotifyListener>());
            listeners = subscribed.get(url);
        }
        listeners.add(listener);

    }

    void doSubscribe(URL url){}

    @Override
    public void unsubscribe(URL url, NotifyListener listener) {

        if (url == null) {
            throw new IllegalArgumentException("unsubscribe url == null");
        }
        if (listener == null) {
            throw new IllegalArgumentException("unsubscribe listener == null");
        }
        if (logger.isInfoEnabled()){
            logger.info("Unsubscribe: " + url);
        }
        Set<NotifyListener> listeners = subscribed.get(url);
        if (listeners != null) {
            listeners.remove(listener);
        }
    }

    void doUnsubscribe(URL url){}
}
