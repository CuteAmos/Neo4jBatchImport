package com.adtdata.neo4j.task;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.CompanyModel;
import com.adtdata.neo4j.csv.model.PersonModel;
import com.adtdata.neo4j.domain.Company;
import com.adtdata.neo4j.domain.GroupPerson;
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
public class PersonTask implements Callable<ResultVo> {

    private Param param;
    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public PersonTask(Param param) {
        this.param = param;
    }

    @Override
    public ResultVo call() {
        if(param == null){
            throw  new NullPointerException("param can not be null.");
        }

        System.out.println(LabelConstant.PERSON.getLabel()+"-"+param.getStart()+"-"+param.getEnd()+"-START");
        long start = System.currentTimeMillis();

        List<GroupPerson> groupPeople = companyService.selectGroupPerson(param);
        PersonModel personModel = new PersonModel(param.getStart(),param.getEnd(),groupPeople);
        personModel.produce();

        long end = System.currentTimeMillis();
        System.out.println(LabelConstant.PERSON.getLabel()+"-"+param.getStart()+"-"+param.getEnd()+"-"+groupPeople.size()+"-END 耗时："+(end -start));

        return new ResultVo(LabelConstant.PERSON.getLabel(),param.getStart(),param.getEnd(),groupPeople.size(),"SUCCESS");
    }
}
