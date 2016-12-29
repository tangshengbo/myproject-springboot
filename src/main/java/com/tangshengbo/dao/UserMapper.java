package com.tangshengbo.dao;

import com.tangshengbo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */
@Mapper
public interface UserMapper  {

    User selectByPrimaryKey(Long id);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(@Param("id") Long id);

    @Insert("INSERT INTO USER(username,PASSWORD,STATUS,descn) VALUES(#{user.username},#{user.password},#{user.status},#{user.descn})")
    int insert(@Param("user")User user);

    @Select("SELECT * FROM user ")
    List<User> findAll();
}
