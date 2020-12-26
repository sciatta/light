package com.sciatta.light.core.common;

/**
 * Created by yangxiaoyu on 2020/12/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * RpcException
 */
public class RpcException extends Exception {
    public RpcException(String message) {
        super(message);
    }
    
    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public RpcException(Throwable cause) {
        super(cause);
    }
}
