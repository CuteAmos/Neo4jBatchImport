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
    private static String defaultBatchConfig;
    private static String defaultDatabase;

    public static String getDefaultBatchConfig() {
        return defaultBatchConfig;
    }

    public void setDefaultBatchConfig(String defaultBatchConfig) {
        ImporterConfig.defaultBatchConfig = defaultBatchConfig;
    }

    public static String getDefaultDatabase() {
        return defaultDatabase;
    }

    public  void setDefaultDatabase(String defaultDatabase) {
        ImporterConfig.defaultDatabase = defaultDatabase;
    }
}
