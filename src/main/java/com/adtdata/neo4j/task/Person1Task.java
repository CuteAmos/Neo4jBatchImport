package com.adtdata.neo4j.task;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.Person1Model;
import com.adtdata.neo4j.csv.model.PersonModel;
import com.adtdata.neo4j.domain.GroupPerson;
import com.adtdata.neo4j.domain.Person;
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
public class Person1Task implements Callable<ResultVo> {

    private Param param;
    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public Person1Task(Param param) {
        this.param = param;
    }

    @Override
    public ResultVo call() {
        if(param == null){
            throw  new NullPointerException("param can not be null.");
        }

        System.out.println(LabelConstant.PERSON1.getLabel()+"-"+param.getStart()+"-"+param.getEnd()+"-START");
        long start = System.currentTimeMillis();

        List<Person> people = companyService.selectPerson(param);
        Person1Model person1Model = new Person1Model(param.getStart(),param.getEnd(),people);
        person1Model.produce();

        long end = System.currentTimeMillis();
        System.out.println(LabelConstant.PERSON1.getLabel()+"-"+param.getStart()+"-"+param.getEnd()+"-"+people.size()+"-END 耗时："+(end -start));

        return new ResultVo(LabelConstant.PERSON1.getLabel(),param.getStart(),param.getEnd(),people.size(),"SUCCESS");
    }
}
