package com.sciatta.light.core.provider.invoker;

import com.sciatta.light.core.common.RpcRequest;
import com.sciatta.light.core.common.RpcResponse;

/**
 * Created by yangxiaoyu on 2020/12/25<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * RpcInvoker
 */
public interface RpcInvoker {
    /**
     * process rpc request, then return rpc request
     *
     * @param rpcRequest
     * @return
     */
    RpcResponse invoke(RpcRequest rpcRequest);
}
