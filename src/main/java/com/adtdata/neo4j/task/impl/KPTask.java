package com.adtdata.neo4j.task.impl;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.BRModel;
import com.adtdata.neo4j.csv.model.KPModel;
import com.adtdata.neo4j.domain.Branch;
import com.adtdata.neo4j.domain.KeyPerson;
import com.adtdata.neo4j.query.Param;
import com.adtdata.neo4j.service.CompanyService;
import com.adtdata.neo4j.task.AbstractTask;
import com.adtdata.neo4j.utils.ApplicationContextUtil;
import com.adtdata.neo4j.vo.ResultVo;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author aixiaobai
 * @date 2021/9/30 11:20
 */
public class KPTask extends AbstractTask {

    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public KPTask(Param param) {
        super(param,LabelConstant.KP);
    }


    @Override
    public int executeTask() {
        List<KeyPerson> comAndPerRelations = companyService.selectKeyPerson(param);
        KPModel kpModel = new KPModel(param.getStart(),param.getEnd(),comAndPerRelations);
        kpModel.produce();
        return comAndPerRelations.size();
    }

}
