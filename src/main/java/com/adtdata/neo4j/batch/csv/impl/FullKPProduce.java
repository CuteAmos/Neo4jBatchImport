package com.adtdata.neo4j.batch.csv.impl;

import com.adtdata.neo4j.batch.csv.AbstractFullProduceCsv;
import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.query.Param;
import com.adtdata.neo4j.service.CompanyService;
import com.adtdata.neo4j.task.BRTask;
import com.adtdata.neo4j.task.KPTask;
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
public class FullKPProduce extends AbstractFullProduceCsv {

    @Resource
    private CompanyService companyService;


    @Override
    public void init() {
        super.init();
        start = 0;
        end = companyService.selectKeyPersonMaxId();
    }

    @Override
    public void clear(String rootPath) {
        super.clear(rootPath);
        FileUtil.deleteDir(new File(rootPath + File.separator + LabelConstant.KP.getTypeAndLabel()));
    }

    @Override
    public Callable<ResultVo> executeTask(Param param) {
        return new KPTask(param);
    }
}