package com.adtdata.neo4j.batch.importer.impl;

import com.adtdata.neo4j.batch.importer.IImporter;
import com.adtdata.neo4j.batch.monitor.ProgressMonitor;
import com.adtdata.neo4j.config.CsvProduceConfig;
import com.adtdata.neo4j.config.ImporterConfig;
import com.adtdata.neo4j.utils.FileUtil;
import com.adtdata.neo4j.utils.LoggerUtil;
import com.adtdata.neo4j.utils.ShellUtil;
import com.adtdata.neo4j.utils.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author aixiaobai
 * @date 2021/10/8 23:20
 */
@Service
@Scope("prototype")
public class FullImporter implements IImporter {


    private static String rootPath;
    private static String csvHead;
    private static String neo4jPath;
    private Map<String,String> nodes;
    private Map<String,String> relations;
    private String database;
    private static String tempDb = "temp.db";



    public void init(){
        rootPath = CsvProduceConfig.getRootPath();
        csvHead = CsvProduceConfig.getHeadPath();
        relations = new HashMap<>();
        nodes = new HashMap<>();
        if(database == null || database.length() == 0){
            database = ImporterConfig.getDefaultDatabase();
        }
        neo4jPath = ImporterConfig.getNeo4jPath();
    }


    @Override
    public void importer() {
        ProgressMonitor.setIsImporterRunning(true);
        init();
        handleNodesAndRelations();
        clear(tempDb);
        ShellUtil.importCsv(tempDb,nodes,relations);
        ProgressMonitor.setIsImporterRunning(false);
    }

    @Override
    public boolean restart() {
        init();
        if (ShellUtil.isRunning()) {
            ShellUtil.stopNeo4j();
        }
        String databasePath = neo4jPath+File.separator+"data"+File.separator+"databases";
        String temp = databasePath + File.separator+tempDb;
        if(new File(temp).exists()){
            FileUtil.renameFile(databasePath+File.separator+database,databasePath+File.separator+database+"_bak");
            FileUtil.renameFile(databasePath+File.separator+tempDb,databasePath+File.separator+database);
        }
        ShellUtil.startNeo4j();
        boolean running = ShellUtil.isRunning();
        return running;
    }




    public void clear(String database){
        FileUtil.deleteDir(new File(neo4jPath+File.separator+"data"+File.separator+"databases"+File.separator+database));
    }



    private void handleNodesAndRelations(){
        File file = new File(rootPath);
        if(file.exists() && file.isDirectory()){
            for (File listFile : file.listFiles()) {
                if(listFile.isDirectory()){
                    String name = listFile.getName();
                    String childFile = getChildFile(listFile);
                    if(!StringUtil.isEmpty(childFile)){
                        if(name.startsWith("node_")){
                            nodes.put(name.substring(5),csvHead+File.separator+name.substring(5)+".csv,"+childFile);
                        }else if(name.startsWith("relation_")){
                            relations.put(name.substring(9),csvHead+File.separator+name.substring(9)+".csv,"+childFile);
                        }
                    }
                }
            }
        }else{
            throw new IllegalArgumentException("Can not find this path ："+rootPath);
        }
    }



    public String getChildFile(File file) {
        StringBuilder sb = new StringBuilder();
        for (File listFile : file.listFiles()) {
            sb.append(listFile.getAbsolutePath()).append(",");
        }
        if(sb.length() > 0){
            return sb.substring(0,sb.length() -1);
        }
        return "";
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

}
