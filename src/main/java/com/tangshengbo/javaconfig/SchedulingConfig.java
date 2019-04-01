package com.tangshengbo.javaconfig;

import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * Created by tangshengbo on 2017/1/11.
 */
@Configuration
@EnableScheduling // 启用定时任务
public class SchedulingConfig {

    private static Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);

    private int count;

    @Async
    @Scheduled(cron = "0 0/1 * * * ?") // 每1分钟执行一次
    public void scheduler() {
        logger.info(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date())
                + ">>>>>>>>>>>>>springBoot scheduled ... " + count++);
    }

}
