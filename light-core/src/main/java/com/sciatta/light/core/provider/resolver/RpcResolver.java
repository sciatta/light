package com.sciatta.light.core.provider.resolver;

import com.sciatta.light.core.common.RpcRequest;

/**
 * Created by yangxiaoyu on 2020/12/25<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * resolve service by rpc request
 */
public interface RpcResolver {
    Object resolve(RpcRequest rpcRequest);
}
