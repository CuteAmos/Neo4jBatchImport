package com.adtdata.neo4j.task;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.GRModel;
import com.adtdata.neo4j.domain.ComAndPerRelation;
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
public class GrTask implements Callable<ResultVo> {

    private Param param;
    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public GrTask(Param param) {
        this.param = param;
    }

    @Override
    public ResultVo call() {
        if(param == null){
            throw  new NullPointerException("param can not be null.");
        }

        System.out.println(LabelConstant.GR.getLabel()+"-"+param.getStart()+"-"+param.getEnd()+"-START");
        long start = System.currentTimeMillis();

        List<ComAndPerRelation> comAndPerRelations = companyService.selectComAndPerRelation(param);
        GRModel grModel = new GRModel(param.getStart(),param.getEnd(),comAndPerRelations);
        grModel.produce();

        long end = System.currentTimeMillis();
        System.out.println(LabelConstant.GR.getLabel()+"-"+param.getStart()+"-"+param.getEnd()+"-"+comAndPerRelations.size()+"-END 耗时："+(end -start));

        return new ResultVo(LabelConstant.GR.getLabel(),param.getStart(),param.getEnd(),comAndPerRelations.size(),"SUCCESS");
    }
}
