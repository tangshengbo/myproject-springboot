package com.tangshengbo.javaconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by tangshengbo on 2017/1/11.
 */
@Configuration
@EnableScheduling // 启用定时任务
public class SchedulingConfig {

    private static Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);


    @Scheduled(cron = "0/5 * * * * ?") // 每5秒执行一次
    public void scheduler() {
        logger.info(">>>>>>>>>>>>>springBoot scheduled ... ");

    }

}
