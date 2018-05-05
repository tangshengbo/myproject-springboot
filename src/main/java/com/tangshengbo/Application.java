package com.tangshengbo;

/**
 * Created by Administrator on 2016/12/20.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@SpringBootApplication
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @RequestMapping("/")
    @ResponseBody
    public String greeting() {
        return "OK";
    }
    /*public String greeting() {
        return "Hello World!";
    }


    public String show(){
        return "Spring Boot 启动>>>>>>";
    }*/

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        //修改Banner的模式为OFF
//        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
//        builder.bannerMode(Banner.Mode.OFF).run(args);
        logger.info("============= SpringBoot Start Success =============");
    }


}
