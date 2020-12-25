package com.sciatta.light.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.sciatta.light.api.RpcRequest;
import com.sciatta.light.api.RpcResponse;
import com.sciatta.light.api.UserService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.lang.reflect.Proxy;

/**
 * Created by yangxiaoyu on 2020/12/21<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Rpc
 */
@Slf4j
public class Rpc {
    static {
        // 解决com.alibaba.fastjson.JSONException: autoType is not support.
        // 白名单
        ParserConfig.getGlobalInstance().addAccept("com.sciatta.light");
    }
    
    public static Object create() {
        return Proxy.newProxyInstance(Rpc.class.getClassLoader(), new Class[]{UserService.class}, (proxy, method, args) -> {
            RpcRequest rpcRequest = new RpcRequest();
            rpcRequest.setServiceClassName(UserService.class.getName());
            rpcRequest.setMethodName(method.getName());
            rpcRequest.setParams(args);
            log.debug("send: " + rpcRequest);
            
            RequestBody requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), JSON.toJSONString(rpcRequest));
            
            Request request = new Request.Builder()
                    .url("http://localhost:8080")
                    .post(requestBody)
                    .build();
            
            Response response = new OkHttpClient().newCall(request).execute();
            
            RpcResponse rpcResponse = null;
            if (response.body() != null) {
                rpcResponse = JSON.parseObject(response.body().string(), RpcResponse.class);
            }
            log.debug("recv: " + rpcResponse);
            
            if (rpcResponse != null) {
                return JSON.parse(rpcResponse.getResult().toString());
            }
            return null;
        });
    }
}
