package com.sciatta.light.core.consumer;

/**
 * Created by yangxiaoyu on 2020/12/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * RpcClient
 */
public interface RpcClient {
    <T> T create(Class<T> klass, String targetUrl);
}
