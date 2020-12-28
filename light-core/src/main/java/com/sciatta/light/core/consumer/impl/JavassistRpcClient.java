package com.sciatta.light.core.consumer.impl;

import com.sciatta.light.core.consumer.AbstractRpcClient;
import com.sciatta.light.core.consumer.RpcClient;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * Created by yangxiaoyu on 2020/12/28<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Proxy based on javassist
 */
@Slf4j
@Service("javassist")
public class JavassistRpcClient extends AbstractRpcClient implements RpcClient {
    
    @Override
    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> klass, String targetUrl) {
        log.debug("javassist proxy engine");
        
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(new Class[]{klass});
        try {
            Object o = proxyFactory.createClass().newInstance();
            if (o instanceof ProxyObject) {
                ProxyObject po = (ProxyObject) o;
                po.setHandler(new JavassistRpcClientMethodHandler(klass, targetUrl));
            }
            return (T) o;
        } catch (InstantiationException | IllegalAccessException e) {
            log.error(e.getMessage());
        }
        return null;
    }
    
    private class JavassistRpcClientMethodHandler extends RpcClientCallbackHandler implements MethodHandler {
        
        public JavassistRpcClientMethodHandler(Class<?> klass, String targetUrl) {
            super(klass, targetUrl);
        }
        
        @Override
        public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {
            return super.callback(method, objects);
        }
    }
}
