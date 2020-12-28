package com.sciatta.light.core.common;

import lombok.Data;

/**
 * Created by yangxiaoyu on 2020/12/21<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * RpcRequest
 */
@Data
public class RpcRequest {
    private String serviceName;
    private String methodName;
    private Object[] params;
}
