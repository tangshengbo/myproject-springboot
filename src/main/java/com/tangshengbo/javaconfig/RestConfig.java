package com.tangshengbo.javaconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2016/12/26.
 */
@Configuration
public class RestConfig {


    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
