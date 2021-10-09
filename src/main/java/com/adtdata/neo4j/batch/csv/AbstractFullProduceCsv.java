package com.adtdata.neo4j.batch.csv;

import com.adtdata.neo4j.config.CsvProduceConfig;
import com.adtdata.neo4j.query.Param;
import com.adtdata.neo4j.vo.ResultVo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author aixiaobai
 * @date 2021/10/8 14:08
 */
public abstract class AbstractFullProduceCsv implements IFullProduceCsv{
    protected long end;
    protected long start;
    protected Integer stepSize;
    private ExecutorService threadPool;
    protected static Boolean multithreading;
    protected static Integer threadCount;
    private static String rootPath;


    @Override
    public void execute(Param param) {
        init(param);
        if(start >= end){
            throw new IllegalArgumentException("START can not be less than or equal to END.");
        }
        long current = start;
        List<Callable<ResultVo>> tasks = new LinkedList<>();
        while (current <= end){
            tasks.add(executeTask(new Param(current, current + stepSize)));
            current += stepSize;
        }
        try {
            List<Future<ResultVo>> futures = threadPool.invokeAll(tasks);
            printLog(futures);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract Callable<ResultVo> executeTask(Param param);

    public void printLog(List<Future<ResultVo>> futures ){
        while (futures.size() > 0){
            Iterator<Future<ResultVo>> iterator = futures.iterator();
            while (iterator.hasNext()){
                Future<ResultVo> next = iterator.next();
                if(next.isDone()){
                    ResultVo resultVo = null;
                    try {
                        resultVo = next.get();
                        System.out.println(Thread.currentThread().getName()+"===="+resultVo.toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    iterator.remove();
                }
            }
        }
    }

    public void init(Param param){
        stepSize = CsvProduceConfig.getStepSize();
        multithreading = CsvProduceConfig.getMultithreading();
        threadCount = CsvProduceConfig.getThreadCount();
        if(stepSize == null || stepSize <= 0){
            stepSize = 30000;
        }
        if(threadCount == null ||threadCount <= 0){
            threadCount = 1;
        }
        if(threadPool == null ){
            if(multithreading == null || !multithreading){
                threadPool = Executors.newSingleThreadExecutor();
            }else{
                threadPool = Executors.newFixedThreadPool(threadCount);
            }
        }
        rootPath = CsvProduceConfig.getRootPath();
        clear(rootPath);
    }

    public void clear(String rootPath){
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
}
