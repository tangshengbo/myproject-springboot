package com.tangshengbo.controller;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */
@Controller
public class HelloController {

    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private ServletContext servletContext;

    @RequestMapping("/hello")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "{\"word\":\"tang\"}") String name, Model model) {
        logger.info("hello");
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping("/getjars")
    public List<String> getJarNames(@RequestParam(value = "name", required = false, defaultValue = "{\"word\":\"tang\"}") String name, Model model) {
        String realPath = servletContext.getRealPath("/WEB-INF/lib/");
        return Lists.newArrayList(new File(realPath).list());
    }
}
