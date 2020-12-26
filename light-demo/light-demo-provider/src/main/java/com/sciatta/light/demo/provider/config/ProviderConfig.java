package com.sciatta.light.demo.provider.config;

import com.sciatta.light.demo.api.OrderService;
import com.sciatta.light.demo.api.UserService;
import com.sciatta.light.demo.provider.service.OrderServiceImpl;
import com.sciatta.light.demo.provider.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangxiaoyu on 2020/12/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ProviderConfig
 */
@Configuration
public class ProviderConfig {
    @Bean("com.sciatta.light.demo.api.UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }
    
    @Bean("com.sciatta.light.demo.api.OrderService")
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
}
