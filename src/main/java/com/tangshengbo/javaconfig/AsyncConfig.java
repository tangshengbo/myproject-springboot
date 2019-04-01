package com.tangshengbo.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Created by Tangshengbo on 2019/4/1
 */
@EnableAsync
@Configuration
public class AsyncConfig {

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setQueueCapacity(10);
        return taskExecutor;
    }
}
