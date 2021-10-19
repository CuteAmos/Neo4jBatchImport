package com.adtdata.neo4j.task.impl;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.CompanyModel;
import com.adtdata.neo4j.csv.model.PersonModel;
import com.adtdata.neo4j.domain.Company;
import com.adtdata.neo4j.domain.GroupPerson;
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
public class PersonTask extends AbstractTask {

    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public PersonTask(Param param) {
        super(param,LabelConstant.PERSON);
    }

    @Override
    public int executeTask() {
        List<GroupPerson> groupPeople = companyService.selectGroupPerson(param);
        PersonModel personModel = new PersonModel(param.getStart(),param.getEnd(),groupPeople);
        personModel.produce();
        return groupPeople.size();
    }

}
