package com.tangshengbo.dao;

import com.tangshengbo.model.User;
import com.tangshengbo.util.MyMapper;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */
public interface UserMyMapper extends MyMapper<User> {

    void insertBatch(List<User> users);

    void updateBatch(List<User> users);
}
