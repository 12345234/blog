package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.User;

/**
 * @author admin
 */
public interface UserService extends IService<User> {

    User checkUser(String username, String password);
}
