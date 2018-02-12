package com.ebbk.lock.manager;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import static com.ebbk.lock.untils.ConfigPropertyUtil.*;
import static com.ebbk.lock.untils.CodeInitUtil.*;
/**
 * Created by 20259174 on 2018/1/31.
 */
public class RedissonManager {

    private static RedissonManager redissonManager;

    private RedissonClient redissonClient;

    private RedissonManager(){
    }

    private void init(){
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(Integer.valueOf(getProperty(scanInterval)))
                .addNodeAddress(getProperty(nodeAddresses).split(",")) //集群节点
                .setSlaveConnectionPoolSize(Integer.valueOf(getProperty(slaveConnectionPoolSize)))
                .setMasterConnectionPoolSize(Integer.valueOf(getProperty(masterconnectionpoolsize)));
        redissonClient = Redisson.create(config);
    }

    public static RedissonManager getRedissonManager(){
        if(redissonManager == null){
            synchronized (RedissonManager.class){
                if(redissonManager == null){
                    redissonManager = new RedissonManager();
                    redissonManager.init();
                }
            }
        }
        return redissonManager;
    }

    public RedissonClient getRedissonClient(){
        return this.redissonClient;
    }
}
