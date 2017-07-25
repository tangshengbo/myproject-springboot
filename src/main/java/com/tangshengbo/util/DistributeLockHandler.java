package com.tangshengbo.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by TangShengBo on 2017-07-25.
 */
@Component
public class DistributeLockHandler {

    private static Logger logger = LoggerFactory.getLogger(DistributeLockHandler.class);

    private static final long LOCK_EXPIRE = 30 * 1000L;//单个业务持有锁的时间30s，防止死锁

    private static final long LOCK_TRY_INTERVAL = 30L;//默认30ms尝试一次

    private static final long LOCK_TRY_TIMEOUT = 20 * 1000L;//默认尝试20s

    @Autowired
    private StringRedisTemplate template;

    public boolean tryLock(Lock lock) {
        return getLock(lock, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    public boolean tryLock(Lock lock, long timeout) {
        return getLock(lock, timeout, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    public boolean tryLock(Lock lock, long timeout, long tryInterval) {
        return getLock(lock, timeout, tryInterval, LOCK_EXPIRE);
    }

    public boolean tryLock(Lock lock, long timeout, long tryInterval, long lockExpireTime) {
        return getLock(lock, timeout, tryInterval, lockExpireTime);
    }

    /**
     * 操作redis获取全局锁
     *
     * @param lock           锁的名称
     * @param timeout        获取的超时时间
     * @param tryInterval    多少ms尝试一次
     * @param lockExpireTime 获取成功后锁的过期时间
     * @return true 获取成功，false获取失败
     */
    private boolean getLock(Lock lock, long timeout, long tryInterval, long lockExpireTime) {
        if (StringUtils.isEmpty(lock.getKey()) || StringUtils.isEmpty(lock.getValue())) {
            return false;
        }

        long startTime = System.currentTimeMillis();
        try {
            do {
                if (!template.hasKey(lock.getKey())) {
                    ValueOperations<String, String> operations = template.opsForValue();
                    operations.set(lock.getKey(), lock.getValue(), lockExpireTime, TimeUnit.MILLISECONDS);
                    return true;
                } else {
                    logger.warn("lock is exist!");
                }
                if (System.currentTimeMillis() - startTime > timeout) {
                    //超时退出
                    return false;
                }
                //重试
                Thread.sleep(tryInterval);
            } while (template.hasKey(lock.getKey()));

        } catch (InterruptedException e) {
            logger.info(e.getMessage());
            return false;
        }
        return false;
    }

    /**
     * 释放锁
     */
    public void releaseLock(Lock lock) {
        if (!StringUtils.isEmpty(lock.getKey())) {
            template.delete(lock.getKey());
        }
    }
}
