package com.ebbk.lock.service.impl;

import com.ebbk.lock.manager.RedissonManager;
import com.ebbk.lock.service.DistributedLockService;
import org.redisson.core.RLock;

import java.util.concurrent.TimeUnit;

/**
 * Created by 20259174 on 2018/2/12.
 */
public class DistributedLockServiceImpl implements DistributedLockService {

    private final String preKey = "redissonKey_";

    private RedissonManager redissonManager = RedissonManager.getRedissonManager();

    @Override
    public boolean lock(long waitTime, long leaseTime, String lockKey) {
        RLock rLock = redissonManager.getRedissonClient().getLock(preKey + lockKey);
        try {
            return rLock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void unlock(String lockKey) {
        RLock rLock = redissonManager.getRedissonClient().getLock(preKey + lockKey);
        try{
            rLock.unlock();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
    }
}
