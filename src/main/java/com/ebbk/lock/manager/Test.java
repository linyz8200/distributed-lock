package com.ebbk.lock.manager;

import com.ebbk.lock.untils.DistributedLockUtil;

/**
 * Created by 20259174 on 2018/2/11.
 */
public class Test {

    private final static  String lockkey = "lockkey";


    public static void main(String arg[]){

        A a1 = new A("线程----111");
        A a2 = new A("线程----222");
        A a3 = new A("线程----333");
        a1.start();
        a2.start();
//        a3.start();

//        RedissonManager redissonManager = RedissonManager.getRedissonManager();
    }



    static class A extends Thread{

        private String threadName;

        public A(String threadName){
            this.threadName = threadName;
        }

        @Override
        public void run() {
            System.out.println("************" + threadName + "**********" + "开始运行");
            try {
                System.out.println("************" + threadName + "**********" + "开始运行");
                boolean flag = DistributedLockUtil.lock(10, 10, lockkey);
                if(flag){
                    System.out.println(threadName + "获取锁成功");
                }else {
                    System.out.println(threadName + "获取锁失败");
                }
            } catch (InterruptedException e) {
                System.out.println(threadName + "发生异常");
                e.printStackTrace();
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            DistributedLockUtil.unlock(lockkey);
            System.out.println("************" + threadName + "**********" + "结束运行运行");
        }
    }
}
