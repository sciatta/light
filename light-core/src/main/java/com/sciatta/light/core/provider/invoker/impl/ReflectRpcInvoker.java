package com.sciatta.light.core.provider.invoker.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sciatta.light.core.common.RpcException;
import com.sciatta.light.core.common.RpcRequest;
import com.sciatta.light.core.common.RpcResponse;
import com.sciatta.light.core.provider.invoker.RpcInvoker;
import com.sciatta.light.core.provider.resolver.RpcResolver;

import static com.sciatta.light.core.utils.ReflectUtil.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by yangxiaoyu on 2020/12/25<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * java reflect based
 */
@Slf4j
@Service
public class ReflectRpcInvoker implements RpcInvoker {
    private final RpcResolver rpcResolver;
    
    public ReflectRpcInvoker(RpcResolver rpcResolver) {
        this.rpcResolver = rpcResolver;
    }
    
    @Override
    public RpcResponse invoke(RpcRequest rpcRequest) {
        log.debug("recv: " + rpcRequest);
        
        RpcResponse rpcResponse = new RpcResponse();
        Object service = rpcResolver.resolve(rpcRequest);
        
        try {
            Object result = invokeMethod(service, rpcRequest.getMethodName(), rpcRequest.getParams());
            rpcResponse.setStatus(RpcResponse.Status.SUCCESS);
            rpcResponse.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage());
            rpcResponse.setStatus(RpcResponse.Status.FAIL);
            rpcResponse.setException(new RpcException(e));
        }
        
        log.debug("send: " + rpcResponse);
        return rpcResponse;
    }
    
}