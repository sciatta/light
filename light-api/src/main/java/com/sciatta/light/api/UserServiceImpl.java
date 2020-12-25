package com.sciatta.light.api;

/**
 * Created by yangxiaoyu on 2020/12/21<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UserServiceImpl
 */
public class UserServiceImpl implements UserService {
    @Override
    public User findUserByName(String name) {
        User user = new User();
        user.setName(name);
        user.setAge(18);
        return user;
    }
}
