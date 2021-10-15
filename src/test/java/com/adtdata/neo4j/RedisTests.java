package com.adtdata.neo4j;

import com.adtdata.neo4j.batch.csv.IFullProduceCsv;
import com.adtdata.neo4j.batch.importer.IImporter;
import com.adtdata.neo4j.config.CsvProduceConfig;
import com.adtdata.neo4j.dao.CompanyDao;
import com.adtdata.neo4j.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Set;

@SpringBootTest
class RedisTests {


    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
       // redisUtil.set("AAA","aaa");
        Set<String> keys = redisUtil.getKeys("COM_CAP_*");
        for (String key : keys) {
            redisUtil.delete(key);
        }
    }








}
