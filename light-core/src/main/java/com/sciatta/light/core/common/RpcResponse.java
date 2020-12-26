package com.sciatta.light.core.common;

import lombok.Data;

/**
 * Created by yangxiaoyu on 2020/12/21<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * RpcResponse
 */
@Data
public class RpcResponse {
    private Object result;
    private Status status;
    private Exception exception;
    
    /**
     * request status
     */
    public enum Status {
        /**
         * process success
         */
        SUCCESS,
        /**
         * process fail
         */
        FAIL
    }
}
