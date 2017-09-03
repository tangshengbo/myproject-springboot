package com.tangshengbo.service;

import com.tangshengbo.model.User;

/**
 * Created by TangShengBo on 2016/12/20.
 */
public interface UserService extends Service<User> {

    void saveBatchUser(int batchCount);

    void updateBatchUser(int batchCount);

    int updateUser(int count);
}
