package com.tangshengbo.service;

/**
 * Created by Tang on 2017/6/26.
 */
public interface RedisService {

    String cache();

    String cacheByAutoKey(String k1, String k2);

    /**
     * 通过key删除
     *
     * @param key
     */
    void del(String key);

    /**
     * 添加key value 并且设置存活时间
     *
     * @param key
     * @param value
     * @param timeOut
     *            单位秒
     */
    void set(String key, String value, long timeOut);

    /**
     * 添加key value
     *
     * @param key
     * @param value
     */
    void set(String key, String value);


    /**
     * 获取redis value (String)
     *
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 当key不存在时设置
     *
     * @param key
     * @param value
     */
    boolean setNX(String key, String value);

    /**
     * 当key不存在时设置
     *
     * @param key
     * @param value
     */
    boolean setNX(String key, String value, long timeOut);

    /**
     * 检查key是否已经存在
     *
     * @param key
     * @return
     */
    boolean exists(String key);

    /**
     * 获取还剩多少存活时间
     *
     * @param key
     * @return
     */
    long ttl(String key);

    /**
     * 查看redis里有多少数据
     */
    long dbSize();

    /**
     * 检查是否连接成功
     *
     * @return
     */
    String ping();
}
