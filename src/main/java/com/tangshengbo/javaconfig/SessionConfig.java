package com.tangshengbo.javaconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by Tang on 2017/6/28.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 300)
public class SessionConfig {
}
