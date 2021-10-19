package com.adtdata.neo4j.task.impl;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.BRModel;
import com.adtdata.neo4j.csv.model.NSHModel;
import com.adtdata.neo4j.domain.Branch;
import com.adtdata.neo4j.domain.ShareHolder;
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
public class NSHTask extends AbstractTask {


    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public NSHTask(Param param) {
        super(param,LabelConstant.NSH);
    }

    @Override
    public int executeTask() {
        List<ShareHolder> comAndPerRelations = companyService.selectSnShareHolder(param);
        NSHModel brModel = new NSHModel(param.getStart(),param.getEnd(),comAndPerRelations);
        brModel.produce();
        return comAndPerRelations.size();
    }

}
