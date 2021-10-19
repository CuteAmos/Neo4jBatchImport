package com.adtdata.neo4j.batch.csv;

import com.adtdata.neo4j.batch.monitor.ProgressMonitor;
import com.adtdata.neo4j.config.CsvProduceConfig;
import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.query.Param;
import com.adtdata.neo4j.utils.FileUtil;
import com.adtdata.neo4j.utils.LoggerUtil;
import com.adtdata.neo4j.vo.ResultVo;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author aixiaobai
 * @date 2021/10/8 14:08
 */
public abstract class AbstractFullProduceCsv implements IFullProduceCsv {
    protected long end;
    protected long start;
    protected Integer stepSize;
    private ExecutorService threadPool;
    protected static Boolean multithreading;
    protected static Integer threadCount;
    private static String rootPath;
    protected LabelConstant labelConstant;


    public AbstractFullProduceCsv(LabelConstant labelConstant) {
        this.labelConstant = labelConstant;
    }

    @Override
    public void execute() {
        init();
        if (start >= end) {
            throw new IllegalArgumentException("START can not be less than or equal to END.");
        }
        long current = start;
        List<Callable<ResultVo>> tasks = new LinkedList<>();
        while (current <= end) {
            tasks.add(executeTask(new Param(current, current + stepSize)));
            current += stepSize;
        }
        ProgressMonitor.init(labelConstant.getLabel(),tasks.size());
        try {
            List<Future<ResultVo>> futures = threadPool.invokeAll(tasks);
            printLog(futures);
        } catch (InterruptedException e) {
            LoggerUtil.getDebugLogger().info("ExecutorService.invokeAll failed : ",e);
        }
    }



    public abstract Callable<ResultVo> executeTask(Param param);

    public void printLog(List<Future<ResultVo>> futures) {
        if (futures != null) {
            while (futures.size() > 0) {
                Iterator<Future<ResultVo>> iterator = futures.iterator();
                while (iterator.hasNext()) {
                    Future<ResultVo> next = iterator.next();
                    if (next.isDone()) {
                        ResultVo resultVo = null;
                        try {
                            resultVo = next.get();
                            LoggerUtil.getDebugLogger().info("导入成功："+resultVo.toString());
                        } catch (InterruptedException|ExecutionException e) {
                            LoggerUtil.getDebugLogger().info("PrintLog failed : ",e);
                        }
                        iterator.remove();
                    }
                }
            }
        }
        threadPool.shutdown();
    }

    public void init() {
        stepSize = CsvProduceConfig.getStepSize();
        multithreading = CsvProduceConfig.getMultithreading();
        threadCount = CsvProduceConfig.getThreadCount();
        if (stepSize == null || stepSize <= 0) {
            stepSize = 30000;
        }
        if (threadCount == null || threadCount <= 0) {
            threadCount = 1;
        }
        if (multithreading == null || !multithreading) {
            threadPool = Executors.newSingleThreadExecutor();
        } else {
            threadPool = Executors.newFixedThreadPool(threadCount);
        }
        rootPath = CsvProduceConfig.getRootPath();
        clear(rootPath);
    }

    public void clear(String rootPath) {
        if(labelConstant != null){
            FileUtil.deleteDir(new File(rootPath + File.separator + labelConstant.getTypeAndLabel()));
        }
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public LabelConstant getLabelConstant() {
        return labelConstant;
    }

    public void setLabelConstant(LabelConstant labelConstant) {
        this.labelConstant = labelConstant;
    }
}
