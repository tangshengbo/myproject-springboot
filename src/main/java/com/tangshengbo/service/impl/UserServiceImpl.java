package com.tangshengbo.service.impl;

import com.tangshengbo.controller.HolleController;
import com.tangshengbo.dao.UserMapper;
import com.tangshengbo.model.User;
import com.tangshengbo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/20.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    private static Logger logger = LoggerFactory.getLogger(HolleController.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserById(Long id) {
        logger.info("UserServiceImpl.selectUserById param:{}",id);
        User user = userMapper.selectByPrimaryKey(id);
        logger.info("UserServiceImpl.selectUserById result:{}",user.toString());
        return user;
    }

    @Override
    public User findById(Long id) {
        logger.info("UserServiceImpl.findById param:{}",id);
        User user = userMapper.findById(id);
        logger.info("UserServiceImpl.findById result:{}",user.toString());
        return user;
    }
}
