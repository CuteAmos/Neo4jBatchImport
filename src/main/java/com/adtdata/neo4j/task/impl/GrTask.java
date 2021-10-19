package com.adtdata.neo4j.task.impl;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.GRModel;
import com.adtdata.neo4j.domain.ComAndPerRelation;
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
public class GrTask extends AbstractTask {


    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public GrTask(Param param) {
        super(param,LabelConstant.GR);
    }

    @Override
    public int executeTask() {
        List<ComAndPerRelation> comAndPerRelations = companyService.selectComAndPerRelation(param);
        GRModel grModel = new GRModel(param.getStart(),param.getEnd(),comAndPerRelations);
        grModel.produce();
        return comAndPerRelations.size();
    }

}
