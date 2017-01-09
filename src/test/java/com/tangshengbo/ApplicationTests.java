package com.tangshengbo;

import com.tangshengbo.model.Model;
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

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.tangshengbo.utils.MD5Util.md5;

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
        user.setPassword(md5("123456789"));
        user.setStatus(1);

        int result = userService.addUser(user);

        List<User> users = userService.findAllUsers();
        for (User u : users) {
            if (md5("123456789").equals(u.getPassword())) {
                logger.warn("md5 equals {}" + u.getPassword());
            }
            logger.info("User:{}", u.toString());
        }

        logger.info("ApplicationTests.testAddUser result:{}", result);
    }

    @Test
    public void testLomBok(){

        Model model = new Model();
        model.setDate(LocalDate.now(Clock.systemDefaultZone()));
        model.setName("tangshengbo");
        model.setId("10000");
        model.setTime(LocalTime.now());
        logger.info("LomBok model{}>>>>", model.toString());
    }

}
