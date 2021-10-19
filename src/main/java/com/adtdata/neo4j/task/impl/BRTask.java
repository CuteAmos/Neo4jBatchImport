package com.adtdata.neo4j.task.impl;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.BRModel;
import com.adtdata.neo4j.csv.model.GRModel;
import com.adtdata.neo4j.domain.Branch;
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
public class BRTask extends AbstractTask {


    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public BRTask(Param param) {
        super(param,LabelConstant.BR);
    }

    @Override
    public int executeTask() {
        List<Branch> comAndPerRelations = companyService.selectBranch(param);
        BRModel brModel = new BRModel(param.getStart(),param.getEnd(),comAndPerRelations);
        brModel.produce();
        return comAndPerRelations.size();
    }

}
