package com.adtdata.neo4j;

import com.adtdata.neo4j.batch.csv.IFullProduceCsv;
import com.adtdata.neo4j.batch.importer.IImporter;
import com.adtdata.neo4j.config.CsvProduceConfig;
import com.adtdata.neo4j.dao.CompanyDao;
import com.adtdata.neo4j.query.Param;
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
    private IFullProduceCsv fullPersonProduce;

    @Resource
    private IFullProduceCsv fullPerson1Produce;


    @Resource
    private IFullProduceCsv fullGrProduce;

    @Resource
    private IFullProduceCsv fullBRProduce;


    @Resource
    private IFullProduceCsv fullSHProduce;

    @Resource
    private IFullProduceCsv fullNSHProduce;


    @Resource
    private IImporter importer;


    @Test
    void contextLoads() {
        fullCompanyNeProduce.execute();
    }

    @Test
    void testCompany() {
        fullCompanyProduce.execute();
    }

    @Test
    void testPerson() {
        fullPersonProduce.execute();

    }



    @Test
    void testAll() throws InterruptedException {

       /* Thread thread = new Thread(() -> fullCompanyProduce.execute());
        thread.start();
        Thread thread0 = new Thread(() -> fullCompanyNeProduce.execute());
        thread0.start();
        Thread thread1 = new Thread(() -> fullPerson1Produce.execute());
        thread1.start();
        Thread thread2 = new Thread(() -> fullPersonProduce.execute());
        thread2.start();
        Thread thread3 = new Thread(() -> fullGrProduce.execute());
        thread3.start();
        Thread thread4 = new Thread(() -> fullBRProduce.execute());
        thread4.start();*/
        Thread thread5 = new Thread(() -> fullSHProduce.execute());
        thread5.start();
   /*     Thread thread6 = new Thread(() -> fullNSHProduce.execute());
        thread6.start();*/


        while (true){
            Thread.sleep(10000);
        }

    }



    @Test
    void importer()  {
        importer.importer();
    }

    @Test
    void testRestart() {
        importer.restart();
    }




}
