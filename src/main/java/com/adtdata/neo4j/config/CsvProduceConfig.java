package com.adtdata.neo4j.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author aixiaobai
 * @date 2021/9/29 14:21
 */
@Component
@ConfigurationProperties(prefix = "csv")
public class CsvProduceConfig {
    private static String rootPath;
    private static Integer stepSize;
    private static Boolean multithreading;
    private static Integer threadCount;
    private static String headPath;

    public static String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        CsvProduceConfig.headPath = headPath;
    }

    public static String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        CsvProduceConfig.rootPath = rootPath;
    }

    public static Integer getStepSize() {
        return stepSize;
    }

    public void setStepSize(Integer stepSize) {
        CsvProduceConfig.stepSize = stepSize;
    }

    public static Boolean getMultithreading() {
        return multithreading;
    }

    public void setMultithreading(Boolean multithreading) {
        CsvProduceConfig.multithreading = multithreading;
    }

    public static Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount) {
        CsvProduceConfig.threadCount = threadCount;
    }
}
