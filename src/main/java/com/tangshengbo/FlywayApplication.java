package com.tangshengbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2016/12/26.
 */
public class FlywayApplication {

    private static Logger logger = LoggerFactory.getLogger(FlywayApplication.class);

    /*public static void main(String[] args) {
//        SpringApplication.run(FlywayApplication.class, args);
        logger.info("============= FlywayApplication Start Success =============");

        // Create the Flyway instance
        Flyway flyway = new Flyway();

        // Point it to the database
        flyway.setDataSource("jdbc:mysql://127.0.0.1:3306/myproject?useUnicode=true&characterEncoding=gbk&zeroDateTimeBehavior=convertToNull", "root", "root");

        // Start the migration
        flyway.migrate();
    }*/
}
