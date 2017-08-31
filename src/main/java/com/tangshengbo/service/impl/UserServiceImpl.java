package com.tangshengbo.service.impl;

import com.google.common.collect.Lists;
import com.tangshengbo.dao.UserMapper;
import com.tangshengbo.model.User;
import com.tangshengbo.service.UserService;
import com.tangshengbo.util.RedisKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserById(Long id) {
        logger.info("UserServiceImpl.selectUserById param:{}", id);
        User user = userMapper.selectByPrimaryKey(id);
        logger.info("UserServiceImpl.selectUserById result:{}", user.toString());
        return user;
    }

    @Override
    public User findUserById(Long id) {
        logger.info("UserServiceImpl.findById param:{}", id);
        User user = userMapper.findById(id);
        logger.info("UserServiceImpl.findById result:{}", user.toString());
        return user;
    }

    @Override
    public int addUser(User user) {
        logger.info("UserServiceImpl.addUser param:{}", user.toString());
        int result = userMapper.insert(user);
        //language=MySQL
        String json = "SELECT * FROM user WHERE id = 1 ";
        logger.info("UserServiceImpl.addUser result:{}", result);
        return result;
    }

    @Cacheable(value = RedisKeys._CACHE_TEST)
    @Override
    public List<User> findAllUsers() {
        logger.info("UserServiceImpl.使用Redis缓存:{}");
        List<User> users = userMapper.findAll();
        logger.info("UserServiceImpl.使用Redis缓存:{}", users.size());
        return users;
    }

    @Override
    public void saveBatchUser(int batchCount) {
        logger.info("批量插入开始...................");
        List<User> users = Lists.newArrayList();
        for (int i = 0; i < batchCount; i++) {
            users.add(new User("唐声波", "tangshengbo", 1, "tangshengbo" + i));
        }
        logger.info("插入总数{}", users.size());
//        userMapper.insertBatch(users);
        saveBatch(users);
        logger.info("批量插入结束...................");
    }

    @Override
    public void updateBatchUser(int batchCount) {
        logger.info("批量更新开始...................");
        List<User> users = Lists.newArrayList();
        for (int i = 0; i < batchCount; i++) {
            User user = new User("唐声波", "lyl", 0, "tangshengbo");
            user.setId((long) i);
            users.add(user);
        }
        logger.info("更新总数{}", users.size());
        userMapper.updateBatch(users);
        logger.info("批量更新结束...................");
    }

    @Override
    public int updateUser(int count) {
        logger.info("更新开始...................");
        for (int i = 0; i < count; i++) {
            User user = new User("唐声波", "lyl", 0, "tangshengbo");
            user.setId((long) i);
            userMapper.updateByPrimaryKey(user);
        }
        logger.info("更新结束...................");
        return 0;
    }

    private void saveBatch(List<User> list) {
        int size = list.size();
        int unitNum = 1000;
        int startIndex = 0;
        int endIndex = 0;
        while (size > 0) {
            if (size > unitNum) {
                endIndex = startIndex + unitNum;
            } else {
                endIndex = startIndex + size;
            }
            List<User> insertBatch = list.subList(startIndex, endIndex);
            userMapper.insertBatch(insertBatch);
            size = size - unitNum;
            startIndex = endIndex;
        }
    }
}
