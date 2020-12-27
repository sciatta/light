package com.sciatta.light.core.consumer.impl;

import com.sciatta.light.core.consumer.AbstractRpcClient;
import com.sciatta.light.core.consumer.RpcClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * Created by yangxiaoyu on 2020/12/27<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Proxy based on CGLIB
 */
@Service("cglib")
@Slf4j
public class CglibRpcClient extends AbstractRpcClient implements RpcClient {
    
    @Override
    @SuppressWarnings("unchecked")
    protected <T> T newProxy(Class<T> klass, String targetUrl) {
        log.debug("cglib proxy engine");
        
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(klass);
        enhancer.setCallback(new CglibRpcClientMethodInterceptor(klass, targetUrl));
        return (T) enhancer.create();
    }
    
    private class CglibRpcClientMethodInterceptor extends RpcClientCallbackHandler implements MethodInterceptor {
        
        public CglibRpcClientMethodInterceptor(Class<?> klass, String targetUrl) {
            super(klass, targetUrl);
        }
        
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            return super.callback(method, objects);
        }
    }
    
}
