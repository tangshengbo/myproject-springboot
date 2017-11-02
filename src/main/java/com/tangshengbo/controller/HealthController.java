package com.tangshengbo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tangshengbo on 2017/11/02.
 */
@RestController
public class HealthController {

    @RequestMapping(value = "/ok", method = RequestMethod.GET)
    public String ok() {
        return "ok";
    }

    @RequestMapping(value = "/throw-exception", method = RequestMethod.GET)
    public String throwException() {
        throw new IllegalArgumentException("why");
    }
}
