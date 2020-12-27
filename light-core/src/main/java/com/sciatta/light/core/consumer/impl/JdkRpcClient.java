package com.sciatta.light.core.consumer.impl;

import com.sciatta.light.core.consumer.AbstractRpcClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yangxiaoyu on 2020/12/27<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Proxy based on JDK Dynamic api
 */
@Service("jdk")
@Slf4j
public class JdkRpcClient extends AbstractRpcClient {
    @Override
    @SuppressWarnings("unchecked")
    protected <T> T newProxy(Class<T> klass, String targetUrl) {
        log.debug("jdk proxy engine");
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{klass},
                new JdkRpcClientInvocationHandler(klass, targetUrl));
    }
    
    private class JdkRpcClientInvocationHandler extends RpcClientCallbackHandler implements InvocationHandler {
        public JdkRpcClientInvocationHandler(Class<?> klass, String targetUrl) {
            super(klass, targetUrl);
        }
        
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return super.callback(method, args);
        }
    }
}
