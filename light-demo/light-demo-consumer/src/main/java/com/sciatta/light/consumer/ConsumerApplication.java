package com.sciatta.light.consumer;

import com.sciatta.light.core.consumer.Rpc;
import com.sciatta.light.demo.api.Order;
import com.sciatta.light.demo.api.OrderService;
import com.sciatta.light.demo.api.User;
import com.sciatta.light.demo.api.UserService;
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
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);    // run spring application
        
        UserService userService = (UserService) Rpc.create(UserService.class);
        User user = userService.findUserByName("rain");
        log.debug("user: " + user);
        
        OrderService orderService = (OrderService) Rpc.create(OrderService.class);
        Order order = orderService.findOrderByIdAndName(1, "iphone");
        log.debug("order: " + order);
    }
}
