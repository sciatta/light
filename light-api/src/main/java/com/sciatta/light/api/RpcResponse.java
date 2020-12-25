package com.sciatta.light.api;

import lombok.Data;

/**
 * Created by yangxiaoyu on 2020/12/21<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * RpcResponse
 */
@Data
public class RpcResponse {
    private Object result;
    private Boolean status;
    private Exception exception;
}
