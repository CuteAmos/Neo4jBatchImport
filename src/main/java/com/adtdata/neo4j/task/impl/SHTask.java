package com.adtdata.neo4j.task.impl;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.NESHModel;
import com.adtdata.neo4j.csv.model.NSHModel;
import com.adtdata.neo4j.csv.model.SHModel;
import com.adtdata.neo4j.domain.ShareHolder;
import com.adtdata.neo4j.query.Param;
import com.adtdata.neo4j.service.CompanyService;
import com.adtdata.neo4j.task.AbstractTask;
import com.adtdata.neo4j.utils.ApplicationContextUtil;
import com.adtdata.neo4j.utils.StringUtil;
import com.adtdata.neo4j.vo.ResultVo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author aixiaobai
 * @date 2021/9/30 11:20
 */
public class SHTask extends AbstractTask {


    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public SHTask(Param param) {
        super(param,LabelConstant.SH);
    }

    @Override
    public int executeTask() {
        List<ShareHolder> shareHolders = companyService.selectShareHolder(param);
        SHModel shModel = new SHModel(param.getStart(), param.getEnd(), shareHolders);
        shModel.produce();
        return shareHolders.size();
    }


}
