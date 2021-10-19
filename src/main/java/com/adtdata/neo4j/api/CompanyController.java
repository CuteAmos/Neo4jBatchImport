package com.adtdata.neo4j.api;

import com.adtdata.neo4j.batch.importer.impl.FullImporter;
import com.adtdata.neo4j.batch.monitor.ProgressMonitor;
import com.adtdata.neo4j.batch.task.TotalTask;
import com.adtdata.neo4j.utils.LoggerUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * @author aixiaobai
 * @date 2021/10/18 13:59
 */
@Controller
public class CompanyController {

    @Resource
    private TotalTask totalTask;

    @Resource
    private FullImporter fullImporter;


    @ApiOperation("0-导入全量数据并重启服务器,只需执行此接口")
    @ResponseBody
    @GetMapping(value = "execute")
    public Object execute(){
        HashMap<String, String> map = new HashMap<>();
        try{
            if (totalTask.isRunning()) {
                map.put("status","running");
                map.put("progress", ProgressMonitor.getProgressStr());
            }else{
                map.put("status","start");
                totalTask.restart();
            }
        }catch (Exception e){
            LoggerUtil.getErrorLogger().info(CompanyController.class.getName()+".importer error :",e);
            map.put("status","error");
        }
        return map;
    }

    @ApiOperation("1-生成全量csv文件")
    @ResponseBody
    @GetMapping(value = "produceCsv")
    public Object produceCsv(){
        HashMap<String, String> map = new HashMap<>();
        try{
            if (totalTask.isRunning()) {
                map.put("status","running");
                map.put("progress", ProgressMonitor.getProgressStr());
            }else{
                map.put("status","start");
                totalTask.execute();
            }
        }catch (Exception e){
            LoggerUtil.getErrorLogger().info(CompanyController.class.getName()+".produceCsv error :",e);
            map.put("status","error");
        }
        return map;
    }

    @ApiOperation("停止生成csv文件")
    @ResponseBody
    @GetMapping(value = "shutdownCsv")
    public Object shutdownCsv(){
        HashMap<String, String> map = new HashMap<>();
        try{
            totalTask.shutdownNow();
            map.put("status","success");
        }catch (Exception e){
            LoggerUtil.getErrorLogger().info(CompanyController.class.getName()+".importerCsv error :",e);
            map.put("status","error");
        }
        return map;
    }



    @ApiOperation("2-导入全量csv文件")
    @ResponseBody
    @GetMapping(value = "importerCsv")
    public Object importerCsv(){
        HashMap<String, String> map = new HashMap<>();
        try{
            if(ProgressMonitor.isImporterRunning()){
                map.put("status","running");
            }else{
                map.put("status","start");
                CompletableFuture.runAsync(()->{fullImporter.importer();});
            }
        }catch (Exception e){
            LoggerUtil.getErrorLogger().info(CompanyController.class.getName()+".importerCsv error :",e);
            map.put("status","error");
        }
        return map;
    }




    @ApiOperation("3-重启服务器")
    @ResponseBody
    @GetMapping(value = "restart")
    public Object restart(){
        HashMap<String, String> map = new HashMap<>();
        try{
            boolean restart = fullImporter.restart();
            if(restart == true){
                map.put("status","success");
            }else{
                map.put("status","fail");
            }

        }catch (Exception e){
            LoggerUtil.getErrorLogger().info(CompanyController.class.getName()+".restart error :",e);
            map.put("status","error");
        }
        return map;
    }

}
