package com.adtdata.neo4j;

import com.adtdata.neo4j.batch.csv.IFullProduceCsv;
import com.adtdata.neo4j.batch.importer.IImporter;
import com.adtdata.neo4j.config.CsvProduceConfig;
import com.adtdata.neo4j.dao.CompanyDao;
import com.adtdata.neo4j.query.Param;
import com.adtdata.neo4j.utils.ImporterUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
class Neo4jApplicationTests {


    @Resource
    private CompanyDao companyDao;

    @Resource
    private CsvProduceConfig csvProduceConfig;

    @Resource
    private IFullProduceCsv fullCompanyNeProduce;

    @Resource
    private IFullProduceCsv fullCompanyProduce;

    @Resource
    private IImporter importer;


    @Test
    void contextLoads() {
        fullCompanyNeProduce.execute(new Param());
    }

    @Test
    void testCompany() {
        fullCompanyProduce.execute(new Param());
    }


    @Test
    void testImporter() throws IOException {
        String[] strs = new String[4];
        strs[0] = "E:\\Idea-workspace\\neo4j\\batch-import\\sample\\batch.properties";
        strs[1] = "E:\\data\\databases\\graph.db";
        strs[2] = "E:\\temp\\company-ne-0-3000000-1633683661785.csv,E:\\temp\\company-ne-18000000-21000000-1633683661824.csv";
        ImporterUtil.importer(strs);
    }

    @Test
    void test() throws IOException {
        importer.importer();
    }



}
