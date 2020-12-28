package com.sciatta.light.demo.provider.controller;

import com.sciatta.light.core.common.RpcRequest;
import com.sciatta.light.core.common.RpcResponse;
import com.sciatta.light.core.provider.invoker.RpcInvoker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangxiaoyu on 2020/12/25<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * spring rest based
 */
@RestController
public class SpringRestEntrypoint {
    private final RpcInvoker invoker;
    
    public SpringRestEntrypoint(RpcInvoker invoker) {
        this.invoker = invoker;
    }
    
    @PostMapping("/")
    public RpcResponse invoke(@RequestBody RpcRequest rpcRequest) {
        return invoker.invoke(rpcRequest);
    }
}
