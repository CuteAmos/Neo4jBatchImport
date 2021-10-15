package com.adtdata.neo4j.task;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.CompanyNeModel;
import com.adtdata.neo4j.dao.CompanyDao;
import com.adtdata.neo4j.domain.Company;
import com.adtdata.neo4j.query.Param;
import com.adtdata.neo4j.service.CompanyService;
import com.adtdata.neo4j.utils.ApplicationContextUtil;
import com.adtdata.neo4j.vo.ResultVo;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author aixiaobai
 * @date 2021/9/30 11:20
 */
public class CompanyNeTask implements Callable<ResultVo> {

    private Param param;
    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public CompanyNeTask(Param param) {
        this.param = param;
    }

    @Override
    public ResultVo call() throws Exception {
        if(param == null){
            throw  new NullPointerException("param can not be null.");
        }
        List<Company> companies = companyService.selectNoEntityCompany(param);
        CompanyNeModel companyNeModel = new CompanyNeModel(param.getStart(),param.getEnd(),companies);
        companyNeModel.produce();
        return new ResultVo(LabelConstant.COMPANY_NE.getLabel(),param.getStart(),param.getEnd(),companies.size(),"SUCCESS");
    }
}
