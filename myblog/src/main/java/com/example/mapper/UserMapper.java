package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author admin
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select username ,password from t_user where username=#{username} and password=#{password}")
    User findByUserNameAndPassword(String username, String password);
}
