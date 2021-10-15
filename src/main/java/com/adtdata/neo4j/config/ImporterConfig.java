package com.adtdata.neo4j.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author aixiaobai
 * @date 2021/10/9 0:06
 */
@Component
@ConfigurationProperties(prefix = "importer")
public class ImporterConfig {

    private static String defaultDatabase;
    private static String neo4jPath;


    public static String getNeo4jPath() {
        return neo4jPath;
    }

    public  void setNeo4jPath(String neo4jPath) {
        ImporterConfig.neo4jPath = neo4jPath;
    }

    public static String getDefaultDatabase() {
        return defaultDatabase;
    }

    public  void setDefaultDatabase(String defaultDatabase) {
        ImporterConfig.defaultDatabase = defaultDatabase;
    }
}
