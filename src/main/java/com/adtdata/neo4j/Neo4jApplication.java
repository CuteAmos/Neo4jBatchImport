package com.adtdata.neo4j;

import com.adtdata.neo4j.utils.ApplicationContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.adtdata.neo4j.dao")
public class Neo4jApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Neo4jApplication.class, args);
    }

}
