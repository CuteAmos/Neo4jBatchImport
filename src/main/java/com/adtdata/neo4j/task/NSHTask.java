package com.adtdata.neo4j.task;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.BRModel;
import com.adtdata.neo4j.csv.model.NSHModel;
import com.adtdata.neo4j.domain.Branch;
import com.adtdata.neo4j.domain.ShareHolder;
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
public class NSHTask implements Callable<ResultVo> {

    private Param param;
    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public NSHTask(Param param) {
        this.param = param;
    }

    @Override
    public ResultVo call() {
        if(param == null){
            throw  new NullPointerException("param can not be null.");
        }

        System.out.println(LabelConstant.NSH.getLabel()+"-"+param.getStart()+"-"+param.getEnd()+"-START");
        long start = System.currentTimeMillis();

        List<ShareHolder> comAndPerRelations = companyService.selectSnShareHolder(param);
        NSHModel brModel = new NSHModel(param.getStart(),param.getEnd(),comAndPerRelations);
        brModel.produce();

        long end = System.currentTimeMillis();
        System.out.println(LabelConstant.NSH.getLabel()+"-"+param.getStart()+"-"+param.getEnd()+"-"+comAndPerRelations.size()+"-END 耗时："+(end -start));

        return new ResultVo(LabelConstant.NSH.getLabel(),param.getStart(),param.getEnd(),comAndPerRelations.size(),"SUCCESS");
    }
}
