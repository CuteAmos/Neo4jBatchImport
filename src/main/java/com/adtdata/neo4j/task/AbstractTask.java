package com.adtdata.neo4j.task;

import com.adtdata.neo4j.batch.monitor.ProgressMonitor;
import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.query.Param;
import com.adtdata.neo4j.vo.ResultVo;

import java.util.concurrent.Callable;

/**
 * @author aixiaobai
 * @date 2021/10/18 11:25
 */
public abstract class AbstractTask implements Callable<ResultVo> {
    protected Param param;
    protected LabelConstant labelConstant;

    public AbstractTask(Param param, LabelConstant labelConstant) {
        this.param = param;
        this.labelConstant = labelConstant;
    }

    public abstract int executeTask();

    @Override
    public ResultVo call() {
        if(param == null || labelConstant == null){
            throw  new NullPointerException("param or labelConstant is null.");
        }

        labelConstant.getLogger().info(labelConstant.getLabel()+"-"+param.getStart()+"-"+param.getEnd()+"-START");
        long start = System.currentTimeMillis();

        long size = executeTask();

        long end = System.currentTimeMillis();
        labelConstant.getLogger().info(labelConstant.getLabel()+"-"+param.getStart()+"-"+param.getEnd()+"-"+size+"-END 耗时："+(end -start));

        ProgressMonitor.increase(labelConstant.getLabel());

        return new ResultVo(LabelConstant.PERSON1.getLabel(),param.getStart(),param.getEnd(),size,"SUCCESS");

    }
}
