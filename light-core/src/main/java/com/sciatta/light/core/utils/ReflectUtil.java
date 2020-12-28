package com.sciatta.light.core.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by yangxiaoyu on 2020/12/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * reflect util
 */
public class ReflectUtil {
    
    public static Object invokeMethod(Object service, String methodName, Object[] params)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = service.getClass().getMethod(methodName, resolveParamClassFromParam(params));
        return method.invoke(service, params);
    }
    
    public static Class<?>[] resolveParamClassFromParam(Object[] params) {
        return Arrays.stream(params).map(Object::getClass).collect(Collectors.toList()).toArray(new Class<?>[params.length]);
    }
}
