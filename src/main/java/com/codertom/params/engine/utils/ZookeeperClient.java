package com.codertom.params.engine.utils;


import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Zookeeper的客户端
 */
public class ZookeeperClient{

    private static volatile ZookeeperClient zkClient;
    private CuratorFramework zkCli;
    private ZookeeperClient(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        zkCli = CuratorFrameworkFactory.newClient(ConfigUtil.getProperty("zookeeper"), retryPolicy);
    }

    public static ZookeeperClient getInstance(){
        if (zkClient==null){
            synchronized (ZookeeperClient.class){
                if (zkClient==null){
                    zkClient = new ZookeeperClient();
                }
            }
        }
        return zkClient;
    }




}
