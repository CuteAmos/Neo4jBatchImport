package com.adtdata.neo4j.batch.importer;

import java.io.IOException;

/**
 * @author aixiaobai
 * @date 2021/10/8 23:17
 */
public interface IImporter {
    void importer() throws IOException;
}
