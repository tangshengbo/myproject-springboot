package com.tangshengbo.javaconfig;

import com.tangshengbo.util.RedisKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tang on 2017/6/26.
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    private static Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);
    /**
     * 在使用@Cacheable时，如果不指定key，则使用找个默认的key生成器生成的key
     */
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator() {
            /**
             * 对参数进行拼接后MD5
             */
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(".").append(method.getName());

                StringBuilder paramsSb = new StringBuilder();
                for (Object param : params) {
                    // 如果不指定，默认生成包含到键值中
                    if (param != null) {
                        paramsSb.append(param.toString());
                    }
                }

                if (paramsSb.length() > 0) {
                    sb.append("_").append(paramsSb);
                }
                logger.info("keyGenerator:{}", sb.toString());
                return sb.toString();
            }
        };
    }

    /**
     * 管理缓存
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate, RedisKeys redisKeys) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        // 设置缓存默认过期时间（全局的）
        rcm.setDefaultExpiration(1800);// 30分钟

        // 根据key设定具体的缓存时间，key统一放在常量类RedisKeys中
        rcm.setExpires(redisKeys.getExpiresMap());

        List<String> cacheNames = new ArrayList<String>(redisKeys.getExpiresMap().keySet());
        rcm.setCacheNames(cacheNames);
        return rcm;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

}