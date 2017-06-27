package com.tangshengbo.javaconfig;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 * Created by Tang on 2017/6/27.
 */
//@Configuration
public class MyBatisConfig  {

    private static Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);
    @Bean
    public PageHelper pageHelper() {
        logger.info("MyBatisConfiguration.pageHelper()");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }

}