package com.adtdata.neo4j.csv;

import com.adtdata.neo4j.config.CsvProduceConfig;
import com.adtdata.neo4j.utils.LoggerUtil;
import com.adtdata.neo4j.utils.StringUtil;

import java.io.*;
import java.util.List;

/**
 * @author aixiaobai
 * @date 2021/9/29 13:50
 */
public abstract class AbstractCsvProduce<T> implements ICsvProduce{
    private long start;
    private long end;
    private String name;
    private static String rootPath= CsvProduceConfig.getRootPath();
    private String subPath;
    private List<T> contents;
    private String type;
    private static String headPath = CsvProduceConfig.getHeadPath();
    protected File csvFile;
    protected FileOutputStream fos  = null;
    protected BufferedOutputStream bos = null;



    public AbstractCsvProduce(long start, long end, String name, String type, List<T> contents) {
        this.start = start;
        this.end = end;
        this.name = name;
        this.type = type;
        this.subPath = this.type + "_" +name;
        this.contents = contents;
        createCsv();
    }

    public void init(){
        if(csvFile == null){
            throw new NullPointerException("csvFile is null.");
        }
        try {
            fos = new FileOutputStream(csvFile,true);
            bos = new BufferedOutputStream(fos);
        } catch (FileNotFoundException e) {
            LoggerUtil.getDebugLogger().info(this.getClass().getName()+".init faild :",e);
        }
    }

    @Override
    public File createCsv() {
        String path = rootPath + File.separator +subPath;
        File pathFile = new File(path);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }

        String fileName = name + "-"+Thread.currentThread().getName()+".csv";
        File file = new File(path + File.separator + fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                LoggerUtil.getDebugLogger().info("create the file : "+fileName+" fail :",e);
            }
        }
        this.csvFile = file;
        init();
        return file;
    }

    @Override
    public void close() {
       try{
           if (bos != null) {
               bos.close();
           }
           if (fos != null) {
               fos.close();
           }
       }catch (IOException e){
           LoggerUtil.getDebugLogger().info(this.getClass().getName()+".close faild :",e);
       }
    }

    @Override
    public void produce() {
        writeCsvContent();
        close();
    }

    @Override
    public String getCsvHead() {
        if(StringUtil.isEmpty(name)){
            throw new NullPointerException("name is null.");
        }
        String csvHead = headPath + File.separator+name+".csv";
        File csvHeadFile = new File(csvHead);
        if(csvHeadFile.exists()&&csvHeadFile.isFile()){
            return csvHeadFile.getAbsolutePath();
        }else{
            throw new IllegalArgumentException("Can not find this file : "+csvHead);
        }
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getContents() {
        return contents;
    }

    public void setContents(List<T> contents) {
        this.contents = contents;
    }

}
