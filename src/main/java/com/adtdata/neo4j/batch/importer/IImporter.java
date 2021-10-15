package com.adtdata.neo4j.batch.importer;

/**
 * @author aixiaobai
 * @date 2021/10/8 23:17
 */
public interface IImporter {
    void importer();
    void restart();
}
