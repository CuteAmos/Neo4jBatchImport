package com.adtdata.neo4j.batch.csv.impl;

import com.adtdata.neo4j.batch.csv.AbstractFullProduceCsv;
import com.adtdata.neo4j.dao.CompanyDao;
import com.adtdata.neo4j.query.Param;
import com.adtdata.neo4j.service.CompanyService;
import com.adtdata.neo4j.task.CompanyNeTask;
import com.adtdata.neo4j.utils.FileUtil;
import com.adtdata.neo4j.vo.ResultVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.concurrent.Callable;

/**
 * @author aixiaobai
 * @date 2021/9/30 10:14
 */
@Component
public class FullCompanyNeProduce extends AbstractFullProduceCsv {

    @Resource
    private CompanyService companyService;


    @Override
    public void init(Param param) {
        super.init(param);
        if(param.getStart() <= param.getEnd()){
            start = 0;
            end = companyService.selectNoEntityCompanyMaxId();
        }else{
            start = param.getStart();
            end = param.getEnd();
        }
    }

    @Override
    public void clear(String rootPath) {
        super.clear(rootPath);
        FileUtil.deleteFileByPrefix("n-companyne-",new File(rootPath));
    }

    @Override
    public Callable<ResultVo> executeTask(Param param) {
        return new CompanyNeTask(param);
    }
}
