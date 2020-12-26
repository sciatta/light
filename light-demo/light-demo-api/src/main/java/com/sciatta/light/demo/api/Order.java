package com.sciatta.light.demo.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yangxiaoyu on 2020/12/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Order
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer orderId;
    private String orderName;
}
