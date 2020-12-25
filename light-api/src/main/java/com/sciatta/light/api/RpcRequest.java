package com.sciatta.light.api;

import lombok.Data;

/**
 * Created by yangxiaoyu on 2020/12/21<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * RpcRequest
 */
@Data
public class RpcRequest {
    private String serviceClassName;
    private String methodName;
    private Object[] params;
}
