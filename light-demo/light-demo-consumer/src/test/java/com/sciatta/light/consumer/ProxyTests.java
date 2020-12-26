package com.sciatta.light.consumer;

import com.sciatta.light.core.consumer.proxy.Rpc;
import com.sciatta.light.demo.api.Order;
import com.sciatta.light.demo.api.OrderService;
import com.sciatta.light.demo.api.User;
import com.sciatta.light.demo.api.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by yangxiaoyu on 2020/12/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ProxyTests
 */
@SpringBootTest
public class ProxyTests {
    
    @Test
    @Disabled
    void testUserService() {
        UserService userService = (UserService) Rpc.create(UserService.class);
        User user = userService.findUserByName("rain");
        assertEquals("rain", user.getName());
        assertEquals(18, user.getAge());
    }
    
    @Test
    @Disabled
    void testOrderService() {
        OrderService orderService = (OrderService) Rpc.create(OrderService.class);
        Order order = orderService.findOrderByIdAndName(1, "iphone");
        assertEquals(1, order.getOrderId());
        assertEquals("iphone", order.getOrderName());
    }
}
