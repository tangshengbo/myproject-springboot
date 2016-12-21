package com.tangshengbo.dao;

import com.tangshengbo.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Administrator on 2016/12/20.
 */

public interface UserMapper {

    User selectByPrimaryKey(Long id);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(@Param("id") Long id);

}
