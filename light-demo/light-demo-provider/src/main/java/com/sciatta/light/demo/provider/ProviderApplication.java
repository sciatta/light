package com.sciatta.light.demo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by yangxiaoyu on 2020/12/22<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ProviderApplication
 */
@SpringBootApplication(scanBasePackages = {"com.sciatta.light"})
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
