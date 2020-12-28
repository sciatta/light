package com.sciatta.light.core.utils;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2020/12/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ReflectUtilTests
 */
public class ReflectUtilTests {
    @Test
    public void testResolveParamClassFromParam() {
        Class<?>[] classes = ReflectUtil.resolveParamClassFromParam(new Object[]{"test"});
        assertSame(classes[0], String.class);
        
        classes = ReflectUtil.resolveParamClassFromParam(new Object[]{"test", 1});
        assertSame(String.class, classes[0]);
        assertSame(Integer.class, classes[1]);
        
        classes = ReflectUtil.resolveParamClassFromParam(new Object[]{"test", Integer.valueOf(2)});
        assertSame(String.class, classes[0]);
        assertSame(Integer.class, classes[1]);
        
        classes = ReflectUtil.resolveParamClassFromParam(new Object[]{"test", Double.valueOf(2)});
        assertSame(String.class, classes[0]);
        assertSame(Double.class, classes[1]);
    }
    
    @Test
    public void testInvokeMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        class Echo {
            public String echo(String msg) {
                return msg + msg;
            }
        }
        
        Echo echo = new Echo();
        Object result = ReflectUtil.invokeMethod(echo, "echo", new String[]{"hello"});
        assertEquals("hellohello", result);
    }
}
