package com.tangshengbo.service;

import com.tangshengbo.model.User;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */
public interface UserService {

    User selectUserById(Long id);

    User findUserById(Long id);

    int  addUser(User user);

    List<User> findAllUsers();

    void saveBatchUser(int batchCount);

    void updateBatchUser(int batchCount);

    int updateUser(int count);
}
