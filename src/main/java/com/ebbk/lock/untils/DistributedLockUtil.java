package com.ebbk.lock.untils;

import com.ebbk.lock.manager.RedissonManager;
import org.redisson.core.RLock;

import java.util.concurrent.TimeUnit;

/**
 * Created by 20259174 on 2018/1/31.
 */
public class DistributedLockUtil {

    private final static String preKey = "redissonKey_";

    private static RedissonManager redissonManager = RedissonManager.getRedissonManager();

    public static boolean lock(long waitTime, long leaseTime, String lockKey) throws InterruptedException {
        RLock rLock = redissonManager.getRedissonClient().getLock(preKey + lockKey);
        return rLock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
    }

    public static void unlock(String lockKey){
        RLock rLock = redissonManager.getRedissonClient().getLock(preKey + lockKey);
        try{
            rLock.unlock();
            System.out.println("解锁成功");
        }catch (IllegalStateException e){
            e.printStackTrace();
            System.out.println("缓存失效");
        }
    }
}
