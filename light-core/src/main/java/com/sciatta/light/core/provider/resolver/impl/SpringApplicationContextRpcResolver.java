package com.sciatta.light.core.provider.resolver.impl;

import com.sciatta.light.core.common.RpcRequest;
import com.sciatta.light.core.provider.resolver.RpcResolver;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created by yangxiaoyu on 2020/12/25<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * spring application context based
 */
@Service
public class SpringApplicationContextRpcResolver implements RpcResolver, ApplicationContextAware {
    private ApplicationContext applicationContext;
    
    @Override
    public Object resolve(RpcRequest rpcRequest) {
        return applicationContext.getBean(rpcRequest.getServiceName());
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
