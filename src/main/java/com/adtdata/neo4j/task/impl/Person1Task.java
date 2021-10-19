package com.adtdata.neo4j.task.impl;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.Person1Model;
import com.adtdata.neo4j.csv.model.PersonModel;
import com.adtdata.neo4j.domain.GroupPerson;
import com.adtdata.neo4j.domain.Person;
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
public class Person1Task extends AbstractTask {


    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public Person1Task(Param param) {
        super(param,LabelConstant.PERSON1);
    }

    @Override
    public int executeTask() {
        List<Person> people = companyService.selectPerson(param);
        Person1Model person1Model = new Person1Model(param.getStart(),param.getEnd(),people);
        person1Model.produce();
        return people.size();
    }

}
