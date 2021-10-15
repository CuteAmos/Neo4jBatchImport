package com.adtdata.neo4j.task;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.BRModel;
import com.adtdata.neo4j.csv.model.GRModel;
import com.adtdata.neo4j.domain.Branch;
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
public class BRTask implements Callable<ResultVo> {

    private Param param;
    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public BRTask(Param param) {
        this.param = param;
    }

    @Override
    public ResultVo call() {
        if(param == null){
            throw  new NullPointerException("param can not be null.");
        }

        System.out.println(LabelConstant.BR.getLabel()+"-"+param.getStart()+"-"+param.getEnd()+"-START");
        long start = System.currentTimeMillis();

        List<Branch> comAndPerRelations = companyService.selectBranch(param);
        BRModel brModel = new BRModel(param.getStart(),param.getEnd(),comAndPerRelations);
        brModel.produce();

        long end = System.currentTimeMillis();
        System.out.println(LabelConstant.BR.getLabel()+"-"+param.getStart()+"-"+param.getEnd()+"-"+comAndPerRelations.size()+"-END 耗时："+(end -start));

        return new ResultVo(LabelConstant.BR.getLabel(),param.getStart(),param.getEnd(),comAndPerRelations.size(),"SUCCESS");
    }
}
