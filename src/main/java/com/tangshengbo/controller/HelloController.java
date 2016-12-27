package com.tangshengbo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2016/12/20.
 */
@Controller
public class HelloController {

    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        logger.info("hello");
        model.addAttribute("name", name);
        return "hello";
    }
}
