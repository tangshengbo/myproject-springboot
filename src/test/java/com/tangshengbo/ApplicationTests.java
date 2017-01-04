package com.tangshengbo;

import com.tangshengbo.model.User;
import com.tangshengbo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static com.tangshengbo.utils.MD5Util.MD5;

/**
 * Created by Administrator on 2016/12/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

    @Autowired
    private UserService userService;

    @Test
    public void testFindUser() throws Exception {
        User user = userService.findUserById(1L);
        logger.info("ApplicationTests.testFindUser result:{}", user.toString());

    }

    @Test
    public void testAddUser() throws Exception {

        User user = new User();
        user.setUsername("唐声波");
        user.setDescn("管理员");
        user.setPassword(MD5("123456789"));
        user.setStatus(1);

        int result = userService.addUser(user);

        List<User> users = userService.findAllUsers();
        for (User u : users) {
            if (MD5("123456789").equals(u.getPassword())) {
                logger.warn("MD5 equals {}" + u.getPassword());
            }
            logger.info("User:{}", u.toString());
        }

        logger.info("ApplicationTests.testAddUser result:{}", result);
    }

}
