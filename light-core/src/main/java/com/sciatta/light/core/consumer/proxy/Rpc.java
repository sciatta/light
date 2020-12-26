package com.sciatta.light.core.consumer.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.sciatta.light.core.common.RpcException;
import com.sciatta.light.core.common.RpcRequest;
import com.sciatta.light.core.common.RpcResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * Created by yangxiaoyu on 2020/12/21<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Rpc
 */
@Slf4j
public class Rpc {
    static {
        // resolve: com.alibaba.fastjson.JSONException: autoType is not support.
        // add fastjson white list
        ParserConfig.getGlobalInstance().addAccept("com.sciatta.light");
    }
    
    public static Object create(Class<?> klass) {
        return Proxy.newProxyInstance(Rpc.class.getClassLoader(), new Class[]{klass}, (proxy, method, args) -> {
            RpcRequest rpcRequest = new RpcRequest();
            rpcRequest.setServiceName(klass.getName());
            rpcRequest.setMethodName(method.getName());
            rpcRequest.setParams(args);
            
            log.debug("send: " + rpcRequest);
            RpcResponse rpcResponse = post(rpcRequest);
            log.debug("recv: " + rpcResponse);
            
            return JSON.parse(rpcResponse.getResult().toString());
        });
    }
    
    private static RpcResponse post(RpcRequest rpcRequest) {
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), JSON.toJSONString(rpcRequest));
        
        Request request = new Request.Builder()
                .url("http://localhost:8080")
                .post(requestBody)
                .build();
        
        RpcResponse rpcResponse;
        try {
            Response response = new OkHttpClient().newCall(request).execute();
            if (response.body() != null) {
                rpcResponse = JSON.parseObject(response.body().string(), RpcResponse.class);
            } else {
                throw new RpcException("invalid response body");
            }
        } catch (IOException | RpcException e) {
            rpcResponse = new RpcResponse();
            rpcResponse.setStatus(RpcResponse.Status.FAIL);
            rpcResponse.setException(e);
        }
        
        assert rpcResponse != null;
        return rpcResponse;
    }
}
