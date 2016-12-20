package com.tangshengbo;

/**
 * Created by Administrator on 2016/12/20.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    @RequestMapping("/")
    public String greeting() {
        return "Hello World!";
    }
    @RequestMapping("/start")
    public String show(){
        return "Spring Boot 启动>>>>>>";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
