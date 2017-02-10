package com.codertom.params.engine;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.util.List;

/**
 * Curator的测试类
 *
 * @author yangzhiming
 * @create 2017-02-10 下午1:38
 **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CuratorTestCase {

    private static CuratorFramework zkCli;

    private static String zkConnectionStr = "localhost:2181";

    @Before
    public void init() {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        zkCli = CuratorFrameworkFactory.builder().connectString(zkConnectionStr).retryPolicy(policy).connectionTimeoutMs(1000).sessionTimeoutMs(6000).build();
        zkCli.start();
    }

    @Test
    public void atestCrateNode() {
        try {
            zkCli.create().creatingParentsIfNeeded().forPath("/curator/test","test".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void btestGetData(){

        try {
            byte[] byteContent = zkCli.getData().forPath("/curator/test");
            String m = new String(byteContent);
            System.out.println(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void ctestCrateCNode() {
        try {
            zkCli.create().creatingParentsIfNeeded().forPath("/curator/test/child1","test".getBytes());
            zkCli.create().creatingParentsIfNeeded().forPath("/curator/test/child2","test".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dtestListCNode(){
        try {
            List<String> listChild = zkCli.getChildren().forPath("/curator/test");
            System.out.println(listChild);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void etestWatchNode(){
        CuratorWatcher watcher = new CuratorWatcher() {
            @Override
            public void process(WatchedEvent event) throws Exception {
                System.out.println(event);
            }
        };

        try {
            zkCli.getData().usingWatcher(watcher).forPath("/curator/test");
            Thread.sleep(2000);
            zkCli.setData().forPath("/curator/test","test1".getBytes());
            zkCli.getData().usingWatcher(watcher).forPath("/curator/test");
            zkCli.setData().forPath("/curator/test","test2".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ftestWatchNode(){
        PathChildrenCache watcher = new PathChildrenCache(zkCli,"/curator/test",true);//true 缓存本地
        try {
            zkCli.getData().forPath("/curator/test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void ztestDelNode(){
        try {
            zkCli.delete().deletingChildrenIfNeeded().forPath("/curator/test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    @After
    public void closeCli(){
        zkCli.close();

    }



}
