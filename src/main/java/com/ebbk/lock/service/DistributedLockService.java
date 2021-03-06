package com.ebbk.lock.service;

/**
 * 可以用于spring bean的注入（单例，多态都可以，内部实例已经实现单例）
 * Created by 20259174 on 2018/2/12.
 */
public interface DistributedLockService {

    /**
     * 加分布式锁，时间单位为秒，
     * @param waitTime 尝试获取锁等待时间
     * @param leaseTime 锁缓存失效时间
     * @param lockKey 锁的后缀key
     * @return
     */
    public boolean lock(long waitTime, long leaseTime, String lockKey);

    /**
     * 解锁
     * @param lockKey
     */
    public void unlock(String lockKey);
}
