package com.sciatta.light.consumer;

import com.sciatta.light.api.User;
import com.sciatta.light.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by yangxiaoyu on 2020/12/21<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UserConsumer
 */
@SpringBootApplication
@Slf4j
public class UserConsumer {
    public static void main(String[] args) {
        SpringApplication.run(UserConsumer.class, args);    // run spring application
        
        UserService userService = (UserService) Rpc.create();
        
        User user = userService.findUserByName("rain");
        
        log.debug("user: " + user);
    }
}
