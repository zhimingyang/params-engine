package com.codertom.params.engine.registry;

import com.codertom.params.engine.common.LocalConf;
import com.codertom.params.engine.common.Path;
import com.codertom.params.engine.common.URL;
import com.codertom.params.engine.rulemanager.RuleManager;
import com.codertom.params.engine.task.PropertiesLocalLoadTask;

import java.util.List;

/**
 * 本地的配置注册中心，读取本地文件，用作配置
 */
public class LocalEngineRegistry extends AbStractEngineRegistry{

    private LocalConf conf;

    //定时拉取本地配置更新当前配置
    private PropertiesLocalLoadTask task;
    //规则管理器
    private RuleManager manager;

    public LocalEngineRegistry(String localPath, RuleManager manager){
        LocalConf localConf = new LocalConf(localPath);
        this.conf = localConf;
        this.manager = manager;
    }

    /**
     * 所有的规则
     * @return
     */
    public List<Path> getAllConf(){
        return conf.getPaths();
    }

    /**
     * 通过URL获取相应的
     * @param url
     * @return
     */
    public Path getConfByUrl(URL url){
        return pramPaths.get(url);
    }

    /**
     * 抽象接口，子类具体场景进行实现
     * @param url
     * @return
     */
    public Path doGetPathByName(URL url){return null;}


    /**
     * 抽象接口，子类具体场景进行实现
     * @return
     */
    public List<Path> doGetAllPaths(){
        return null;
    }

    /**
     *
     * @param url
     */
    void doRegist(URL url){}

    /**
     *
     * @param url
     */
    void doUnRegist(URL url){}

    /**
     *
     * @param url
     */
    void doSubscribe(URL url){}

    /**
     *
     * @param url
     */
    void doUnsubscribe(URL url){}

}
