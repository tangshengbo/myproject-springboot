package com.tangshengbo;

import com.tangshengbo.controller.HolleController;
import com.tangshengbo.model.User;
import com.tangshengbo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2016/12/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTests {
    private static Logger logger = LoggerFactory.getLogger(HolleController.class);

    @Autowired
    private UserService userService;

    @Test
    public void testFindUser() throws Exception {
        User user = userService.findUserById(1L);
        logger.info("ApplicationTests.testFindUser result:{}",user.toString());

    }

    @Test
    public void testAddUser() throws Exception {

        User user = new User();
        user.setUsername("唐声波");
        user.setDescn("管理员");
        user.setPassword("123456");
        user.setStatus(1);

        int result = userService.addUser(user);

        List<User> users =  userService.findAllUsers();
        for(User u : users){
            logger.info("User:{}",u.toString());
        }

        logger.info("ApplicationTests.testAddUser result:{}",result);
    }

}
