package com.sciatta.light.core.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.sciatta.light.core.common.RpcException;
import com.sciatta.light.core.common.RpcRequest;
import com.sciatta.light.core.common.RpcResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by yangxiaoyu on 2020/12/27<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * AbstractRpcClient
 */
@Slf4j
public abstract class AbstractRpcClient implements RpcClient {
    static {
        // add fastjson white list for resolving com.alibaba.fastjson.JSONException: autoType is not support.
        ParserConfig.getGlobalInstance().addAccept("com.sciatta.light");
    }
    
    protected abstract class RpcClientCallbackHandler {
        private final Class<?> klass;
        private final String targetUrl;
        
        public RpcClientCallbackHandler(Class<?> klass, String targetUrl) {
            this.klass = klass;
            this.targetUrl = targetUrl;
        }
        
        public Object callback(Method method, Object[] args) {
            // 1 step -- get rpc request
            RpcRequest rpcRequest = toRpcRequest(klass, method, args);
            // 2 step -- post rpc request to server
            RpcResponse rpcResponse = post(rpcRequest, targetUrl);
            // 3 step -- rpc response to object
            return toObject(rpcResponse);
        }
    }
    
    protected RpcRequest toRpcRequest(Class<?> klass, Method method, Object[] args) {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setServiceName(klass.getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParams(args);
        return rpcRequest;
    }
    
    protected RpcResponse post(RpcRequest rpcRequest, String targetUrl) {
        log.debug("send: " + rpcRequest);
        
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), JSON.toJSONString(rpcRequest));
        
        Request request = new Request.Builder()
                .url(targetUrl)
                .post(requestBody)
                .build();
        
        RpcResponse rpcResponse;
        try {
            Response response = new OkHttpClient().newCall(request).execute();
            if (response.body() != null) {
                rpcResponse = JSON.parseObject(response.body().string(), RpcResponse.class);
            } else {
                rpcResponse = buildFailRpcResponse(new RpcException("invalid response body"));
            }
        } catch (IOException e) {
            rpcResponse = buildFailRpcResponse(new RpcException(e.getMessage()));
        }
    
        log.debug("recv: " + rpcResponse);
        
        assert rpcResponse != null;
        return rpcResponse;
    }
    
    protected Object toObject(RpcResponse rpcResponse) {
        if (rpcResponse.getStatus().equals(RpcResponse.Status.FAIL)) {
            RpcException e = rpcResponse.getException();
            log.error(e.getMessage());
            throw e;
        }
        return JSON.parse(rpcResponse.getResult().toString());
    }
    
    private RpcResponse buildFailRpcResponse(RpcException e) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setStatus(RpcResponse.Status.FAIL);
        rpcResponse.setException(e);
        return rpcResponse;
    }
}
