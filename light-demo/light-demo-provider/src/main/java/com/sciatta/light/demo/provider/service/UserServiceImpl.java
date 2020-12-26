package com.sciatta.light.demo.provider.service;

import com.sciatta.light.demo.api.User;
import com.sciatta.light.demo.api.UserService;

/**
 * Created by yangxiaoyu on 2020/12/21<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UserServiceImpl
 */
public class UserServiceImpl implements UserService {
    @Override
    public User findUserByName(String name) {
        return new User(name, 18);
    }
}
