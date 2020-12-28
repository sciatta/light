package com.sciatta.light.demo.api;

/**
 * Created by yangxiaoyu on 2020/12/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OrderService
 */
public interface OrderService {
    Order findOrderByIdAndName(Integer orderId, String orderName);
}
