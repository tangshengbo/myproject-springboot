package com.tangshengbo.service;

import com.tangshengbo.model.User;

/**
 * Created by Administrator on 2016/12/20.
 */
public interface UserService {

    User selectUserById(Long id);

    User findById(Long id);

}
