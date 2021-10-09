package com.adtdata.neo4j.config;

import com.adtdata.neo4j.utils.ApplicationContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author aixiaobai
 * @date 2021/9/29 15:16
 */
@Component
public class GlobalConfig {

    @Bean
    public ApplicationContextUtil getApplicationContext(){
        return new ApplicationContextUtil();
    }


}
