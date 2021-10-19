package com.adtdata.neo4j.batch.task;

import com.adtdata.neo4j.batch.csv.IFullProduceCsv;
import com.adtdata.neo4j.batch.importer.IImporter;
import com.adtdata.neo4j.batch.monitor.ProgressMonitor;
import com.adtdata.neo4j.utils.LoggerUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * @author aixiaobai
 * @date 2021/10/15 18:03
 */
@Component
public class TotalTask {

    @Resource
    private IFullProduceCsv fullCompanyNeProduce;

    @Resource
    private IFullProduceCsv fullCompanyProduce;

    @Resource
    private IFullProduceCsv fullPersonProduce;

    @Resource
    private IFullProduceCsv fullPerson1Produce;


    @Resource
    private IFullProduceCsv fullGrProduce;

    @Resource
    private IFullProduceCsv fullBRProduce;


    @Resource
    private IFullProduceCsv fullSHProduce;

     @Resource
    private IFullProduceCsv fullNSHProduce;

    @Resource
    private IFullProduceCsv fullKPProduce;


    @Resource
    private IImporter fullImporter;

    private static ExecutorService executorService;


    public void execute() {
        try {
            if (executorService == null || executorService.isShutdown()) {
                executorService = new ThreadPoolExecutor(9, 9,
                        0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());
            }
            ProgressMonitor.clear();
            executorService.execute(() -> fullCompanyProduce.execute());
            executorService.execute(() -> fullCompanyNeProduce.execute());
            executorService.execute(() -> fullPerson1Produce.execute());
            executorService.execute(() -> fullPersonProduce.execute());
            executorService.execute(() -> fullGrProduce.execute());
            executorService.execute(() -> fullBRProduce.execute());
            executorService.execute(() -> fullSHProduce.execute());
            executorService.execute(() -> fullNSHProduce.execute());
            executorService.execute(() -> fullKPProduce.execute());
        } catch (Exception e) {
            LoggerUtil.getErrorLogger().error("TotalTask.execute fail.");
        }
    }


    public void restart(){
        CompletableFuture.runAsync(
                ()->{
                    if(isRunning()){
                        executorService.shutdown();
                    }
                }
        ).thenRun(() ->{
            fullImporter.importer();
        }).thenRun(()->{
            fullImporter.restart();
        });
    }


    public boolean isRunning() {
        if(executorService == null){
            return false;
        }
        return !executorService.isTerminated();
    }

    public void shutdownNow() {
        if(executorService != null && !executorService.isShutdown()){
            executorService.shutdownNow();
        }
    }
}
