package com.sciatta.light.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sciatta.light.api.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yangxiaoyu on 2020/12/22<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UserProvider
 */
@SpringBootApplication
@RestController
@Slf4j
public class UserProvider {
    private final ApplicationContext context;
    
    public UserProvider(ApplicationContext context) {
        this.context = context;
    }
    
    @PostMapping("/")
    private RpcResponse findUserByName(@RequestBody RpcRequest rpcRequest) {
        log.debug("recv: " + rpcRequest);
        String serviceClassName = rpcRequest.getServiceClassName();
        String methodName = rpcRequest.getMethodName();
        Object[] params = rpcRequest.getParams();
        
        UserService userService = context.getBean(serviceClassName, UserService.class);
        User user = null;
        try {
            Method method = UserServiceImpl.class.getMethod(methodName, String.class);
            String param= (String) params[0];
            user = (User) method.invoke(userService, param);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    
        RpcResponse response = new RpcResponse();
        response.setStatus(true);
        response.setResult(JSON.toJSONString(user, SerializerFeature.WriteClassName));
        log.debug("send: " + response);
        return response;
    }
    
    @Bean("com.sciatta.light.api.UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(UserProvider.class, args);
    }
}
