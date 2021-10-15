package com.adtdata.neo4j.task;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.model.NESHModel;
import com.adtdata.neo4j.csv.model.NSHModel;
import com.adtdata.neo4j.csv.model.SHModel;
import com.adtdata.neo4j.domain.ShareHolder;
import com.adtdata.neo4j.query.Param;
import com.adtdata.neo4j.service.CompanyService;
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
public class SHTask implements Callable<ResultVo> {

    private Param param;
    private static CompanyService companyService = ApplicationContextUtil.getBean(CompanyService.class);

    public SHTask(Param param) {
        this.param = param;
    }

    @Override
    public ResultVo call() {
        if(param == null){
            throw  new NullPointerException("param can not be null.");
        }

        System.out.println(LabelConstant.SH.getLabel()+"-"+param.getStart()+"-"+param.getEnd()+"-START");
        long start = System.currentTimeMillis();

        List<ShareHolder> shareHolders = companyService.selectShareHolder(param);

        List<ShareHolder> shList = new LinkedList<>();
        List<ShareHolder> neshList = new LinkedList<>();

        if (shareHolders != null){
            for (ShareHolder shareHolder : shareHolders) {
                if(shareHolder.getType() == 1){
                    shList.add(shareHolder);
                }else{
                    neshList.add(shareHolder);
                }
            }
        }

        SHModel shModel = new SHModel(param.getStart(), param.getEnd(), shList);
        NESHModel neshModel = new NESHModel(param.getStart(), param.getEnd(), neshList);
        shModel.produce();
        neshModel.produce();

        long end = System.currentTimeMillis();
        System.out.println(LabelConstant.SH.getLabel()+"-"+param.getStart()+"-"+param.getEnd()+"-"+shareHolders.size()+"-END 耗时："+(end -start));

        return new ResultVo(LabelConstant.SH.getLabel(),param.getStart(),param.getEnd(),shareHolders.size(),"SUCCESS");
    }
}
