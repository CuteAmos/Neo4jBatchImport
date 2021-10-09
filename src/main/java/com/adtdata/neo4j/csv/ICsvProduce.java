package com.adtdata.neo4j.csv;


import java.io.File;
import java.util.List;

/**
 * @author aixiaobai
 * @date 2021/9/29 11:05
 */
public interface ICsvProduce {

    File createCsv();

    void writeCsvHead();

    void writeCsvContent();

    void close();

    void produce();

}
