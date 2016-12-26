package com.tangshengbo.controller;

import com.tangshengbo.model.User;
import com.tangshengbo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/12/20.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/name")
    public String getUserName() {
        return "唐声波";
    }

    @RequestMapping("/users/{username}")
    public String userProfile(@PathVariable("username") String username) {
        return String.format("user %s", username);
    }

    @RequestMapping("/posts/{id}")
    public String post(@PathVariable("id") int id) {
        return String.format("post %d", id);
    }

    @RequestMapping("/getuser/{id}")
    public User getUser(@PathVariable("id") Long id) {
        logger.info("UserController.getUser param:{}", id);
        //mybatis xml
        //User user = userService.selectUserById(id);
        //mybatis annotation
        User user = userService.findUserById(id);
        logger.info("UserController.getUser result:{}", user.toString());
        return user;
    }


}
