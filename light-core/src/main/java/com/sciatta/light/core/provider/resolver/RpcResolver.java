package com.sciatta.light.core.provider.resolver;

import com.sciatta.light.core.common.RpcRequest;

/**
 * Created by yangxiaoyu on 2020/12/25<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * RpcResolver
 */
public interface RpcResolver {
    /**
     * resolve service by rpc request
     *
     * @param rpcRequest
     * @return
     */
    Object resolve(RpcRequest rpcRequest);
}
