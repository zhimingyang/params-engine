package com.codertom.params.engine;

import redis.clients.jedis.Jedis;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Redis分布式锁
 */
public class RedisLockTest {

    private Jedis jedisCli = new Jedis("localhost",6381);

    private int expireTime = 1;

    /**
     * 获取锁
     * @param lockID
     * @return
     */
    public boolean lock(String lockID){
        while(true){
            long returnFlag = jedisCli.setnx(lockID,"1");
            if (returnFlag == 1){
                System.out.println(Thread.currentThread().getName() + " get lock....");
                return true;
            }
            System.out.println(Thread.currentThread().getName() + " is trying lock....");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * 超时获取锁
     * @param lockID
     * @param timeOuts
     * @return
     */
    public boolean lock(String lockID,long timeOuts){
        long current = System.currentTimeMillis();
        long future = current + timeOuts;
        long timeStep = 500;
        CountDownLatch latch = new CountDownLatch(1);
        while(future > current){
            long returnFlag = jedisCli.setnx(lockID,"1");
            if (returnFlag == 1){
                System.out.println(Thread.currentThread().getName() + " get lock....");
                jedisCli.expire(lockID,expireTime);
                return true;
            }
            System.out.println(Thread.currentThread().getName() + " is trying lock....");
            try {
                latch.await(timeStep, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            current = current + timeStep;
        }
        return false;
    }

    public void unlock(String lockId){
        long flag = jedisCli.del(lockId);
        if (flag>0){
            System.out.println(Thread.currentThread().getName() + " release lock....");
        }else {
            System.out.println(Thread.currentThread().getName() + " release lock fail....");
        }
    }

    /**
     * 线程工厂,命名线程
     */
    public static class MyThreadFactory implements ThreadFactory{
        public static AtomicInteger count = new AtomicInteger();
        @Override
        public Thread newThread(Runnable r) {
            count.getAndIncrement();
            Thread thread = new Thread(r);
            thread.setName("Thread-lock-test "+count);
            return thread;
        }
    }

    public static void main(String args[]){
        final String lockID = "test1";
        Runnable task = () ->{
            RedisLockTest testCli = new RedisLockTest();
            testCli.lock(lockID);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            testCli.unlock(lockID);
        };

        MyThreadFactory factory = new MyThreadFactory();
        ExecutorService services = Executors.newFixedThreadPool(10);
        for (int i = 0;i<3;i++)
            services.execute(factory.newThread(task));
    }

}
