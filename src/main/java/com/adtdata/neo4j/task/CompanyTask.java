package com.adtdata.neo4j.task;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.CompanyModel;
import com.adtdata.neo4j.csv.model.CompanyNeModel;
import com.adtdata.neo4j.dao.CompanyDao;
import com.adtdata.neo4j.domain.Company;
import com.adtdata.neo4j.query.Param;
import com.adtdata.neo4j.service.CompanyService;
import com.adtdata.neo4j.utils.ApplicationContextUtil;
import com.adtdata.neo4j.vo.ResultVo;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author aixiaobai
 * @date 2021/9/30 11:20
 */
public class CompanyTask implements Callable<ResultVo> {

    private Param param;
    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public CompanyTask(Param param) {
        this.param = param;
    }

    @Override
    public ResultVo call() {
        if(param == null){
            throw  new NullPointerException("param can not be null.");
        }
        System.out.println(param.getStart()+"-"+param.getEnd()+"-START");
        long start = System.currentTimeMillis();

        List<Company> companies = companyService.selectCompany(param);
        CompanyModel companyModel = new CompanyModel(param.getStart(),param.getEnd(),companies);
        companyModel.produce();

        long end = System.currentTimeMillis();
        System.out.println(LabelConstant.COMPANY.getLabel()+"-"+param.getStart()+"-"+param.getEnd()+"-"+companies.size()+"-END 耗时："+(end -start));

        return new ResultVo(LabelConstant.COMPANY.getLabel(),param.getStart(),param.getEnd(),companies.size(),"SUCCESS");
    }
}
