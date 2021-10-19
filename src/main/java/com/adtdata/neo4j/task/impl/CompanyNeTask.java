package com.adtdata.neo4j.task.impl;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.CompanyNeModel;
import com.adtdata.neo4j.dao.CompanyDao;
import com.adtdata.neo4j.domain.Company;
import com.adtdata.neo4j.query.Param;
import com.adtdata.neo4j.service.CompanyService;
import com.adtdata.neo4j.task.AbstractTask;
import com.adtdata.neo4j.utils.ApplicationContextUtil;
import com.adtdata.neo4j.vo.ResultVo;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author aixiaobai
 * @date 2021/9/30 11:20
 */
public class CompanyNeTask extends AbstractTask {

    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public CompanyNeTask(Param param) {
        super(param,LabelConstant.COMPANY_NE);
    }

    @Override
    public int executeTask() {
        List<Company> companies = companyService.selectNoEntityCompany(param);
        CompanyNeModel companyNeModel = new CompanyNeModel(param.getStart(),param.getEnd(),companies);
        companyNeModel.produce();
        return companies.size();
    }

}
