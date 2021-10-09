package com.adtdata.neo4j.batch.importer.impl;

import com.adtdata.neo4j.batch.importer.IImporter;
import com.adtdata.neo4j.config.CsvProduceConfig;
import com.adtdata.neo4j.config.ImporterConfig;
import com.adtdata.neo4j.utils.FileUtil;
import com.adtdata.neo4j.utils.ImporterUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author aixiaobai
 * @date 2021/10/8 23:20
 */
@Service
@Scope("prototype")
public class FullImporter implements IImporter {

    private static String rootPath;
    private StringBuilder nodes;
    private StringBuilder relations;
    private String batchConfig;
    private String database;



    public void init(){
        rootPath = CsvProduceConfig.getRootPath();
        nodes = new StringBuilder();
        relations = new StringBuilder();
        if(batchConfig == null || batchConfig.length() == 0){
            batchConfig = ImporterConfig.getDefaultBatchConfig();
        }
        if(database == null || database.length() == 0){
            database = ImporterConfig.getDefaultDatabase();
        }
        clear();
    }

    public void clear(){
        FileUtil.deleteFile(new File(database));
    }

    @Override
    public void importer() throws IOException {
        init();
        File file = new File(rootPath);
        handleFile(file.listFiles());
        String[] args = new String[4];
        args[0] = batchConfig;
        args[1] = database;
        args[2] = getNodes();
        args[3] = getRelations();
        ImporterUtil.importer(args);
    }




    private void handleFile(File[]  files){
        if(files == null || files.length == 0){
            return;
        }
        for (File file : files) {
            if(file.isDirectory()){
                handleFile(file.listFiles());
            }else if(file.getName().startsWith("n-")){
                nodes.append(file.getAbsoluteFile()).append(",");
            }else if(file.getName().startsWith("r-")){
                relations.append(file.getAbsoluteFile()).append(",");
            }
        }
    }

    public String getNodes() {
        if(nodes.length() > 0){
            return nodes.substring(0,nodes.length()-1);
        }
        return nodes.toString();
    }

    public String getRelations() {
        if(relations.length() > 0){
            return relations.substring(0,relations.length()-1);
        }
        return relations.toString();
    }

    public String getBatchConfig() {
        return batchConfig;
    }

    public void setBatchConfig(String batchConfig) {
        this.batchConfig = batchConfig;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

}
