package com.adtdata.neo4j.task.impl;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.CompanyModel;
import com.adtdata.neo4j.csv.model.CompanyNeModel;
import com.adtdata.neo4j.dao.CompanyDao;
import com.adtdata.neo4j.domain.Company;
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
public class CompanyTask extends AbstractTask {

    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public CompanyTask(Param param) {
        super(param,LabelConstant.COMPANY);
    }

    @Override
    public int executeTask() {
        List<Company> companies = companyService.selectCompany(param);
        CompanyModel companyModel = new CompanyModel(param.getStart(),param.getEnd(),companies);
        companyModel.produce();
        return companies.size();
    }

}
