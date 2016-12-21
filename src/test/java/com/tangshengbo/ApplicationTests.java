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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/12/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class ApplicationTests {
    private static Logger logger = LoggerFactory.getLogger(HolleController.class);

    @Autowired
    private UserService userService;

    @Test
    @Rollback
    public void testFindUser() throws Exception {
        User user = userService.findById(1L);
        logger.info("ApplicationTests.testFindUser result:{}",user.toString());

    }

}
