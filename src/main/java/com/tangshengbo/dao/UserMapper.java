package com.tangshengbo.dao;

import com.tangshengbo.model.User;

/**
 * Created by Administrator on 2016/12/20.
 */
public interface UserMapper {

    User selectByPrimaryKey(Long id);

}
