package com.codertom.params.engine;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.CuratorWatcher;
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
 *  方法命名优点sb，为了保证执行顺序，只能委屈一下了。
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
        //设置重试机制
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        //指定参数来创建相关的Client，简单的创建方式为newClient
        zkCli = CuratorFrameworkFactory.builder().connectString(zkConnectionStr).retryPolicy(policy).connectionTimeoutMs(1000).sessionTimeoutMs(6000).build();
        //使用之前一定要先start
        zkCli.start();
    }

    @Test
    public void atestCrateNode() {
        try {
            //创建新节点，这里是可以进行递归创建的，整个流程优点像流式的意思，最后调用forPath进行执行
            zkCli.create().creatingParentsIfNeeded().forPath("/curator/test","test".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void btestGetData(){

        try {
            //读取指定节点的数据
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
            //创建子节点
            zkCli.create().creatingParentsIfNeeded().forPath("/curator/test/child1","test".getBytes());
            zkCli.create().creatingParentsIfNeeded().forPath("/curator/test/child2","test".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dtestListCNode(){
        try {
            //显示指定列表下的所有节点
            List<String> listChild = zkCli.getChildren().forPath("/curator/test");
            System.out.println(listChild);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void etestWatchNode(){

        //对相应的节点做Watch，遗憾这种方式的Watcher仍然是单次有效的
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
        //另一种创建Watcher的方式，同样是单次有效的
        CuratorListener listener = new CuratorListener()
        {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception
            {
                System.out.println(event.getWatchedEvent().toString());
            }
        };
        zkCli.getCuratorListenable().addListener(listener);
        try {
             zkCli.getData().watched().forPath("/curator/test");
             zkCli.setData().forPath("/curator/test","test4".getBytes());
             zkCli.setData().forPath("/curator/test","test5".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void ztestDelNode(){
        //进行节点删除
        try {
            zkCli.delete().deletingChildrenIfNeeded().forPath("/curator/test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @After
    public void closeCli(){
        //关闭Client
        zkCli.close();

    }



}
