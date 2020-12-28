package com.sciatta.light.demo.provider.service;

import com.sciatta.light.core.common.RpcException;
import com.sciatta.light.demo.api.Order;
import com.sciatta.light.demo.api.OrderService;

/**
 * Created by yangxiaoyu on 2020/12/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OrderServiceImpl
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public Order findOrderByIdAndName(Integer orderId, String orderName) {
        Order order = new Order();
        if (orderId.equals(1)) {
            order.setOrderId(orderId);
            order.setOrderName(orderName);
        } else {
            throw new RpcException("no order: " + orderId);
        }
        return order;
    }
}
