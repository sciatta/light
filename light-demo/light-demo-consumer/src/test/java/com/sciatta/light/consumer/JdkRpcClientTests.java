package com.sciatta.light.consumer;

import com.sciatta.light.core.consumer.RpcClient;
import com.sciatta.light.demo.api.Order;
import com.sciatta.light.demo.api.OrderService;
import com.sciatta.light.demo.api.User;
import com.sciatta.light.demo.api.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by yangxiaoyu on 2020/12/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * JdkRpcClientTests
 */
@SpringBootTest
public class JdkRpcClientTests {
    @Autowired
    private RpcClient rpcClient;
    
    @Test
    @Disabled
    void testUserService() {
        UserService userService = rpcClient.create(UserService.class, "http://localhost:8080");
        User user = userService.findUserByName("rain");
        assertEquals("rain", user.getName());
        assertEquals(18, user.getAge());
    }
    
    @Test
    @Disabled
    void testOrderService() {
        OrderService orderService = rpcClient.create(OrderService.class, "http://localhost:8080");
        Order order = orderService.findOrderByIdAndName(1, "iphone");
        assertEquals(1, order.getOrderId());
        assertEquals("iphone", order.getOrderName());
    }
}
