package com.adtdata.neo4j.csv;

import com.adtdata.neo4j.config.CsvProduceConfig;

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
    protected File csvFile;
    protected FileOutputStream fos  = null;
    protected BufferedOutputStream bos = null;


    public AbstractCsvProduce() {
    }

    public AbstractCsvProduce(long start, long end, String name, String subPath, List<T> contents) {
        this.start = start;
        this.end = end;
        this.name = name;
        this.subPath = subPath;
        this.contents = contents;
        createCsv();
    }

    public void init(){
        if(csvFile == null){
            throw new NullPointerException("csvFile is null.");
        }
        try {
            fos = new FileOutputStream(csvFile);
            bos = new BufferedOutputStream(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File createCsv() {
        String path = rootPath;
        if(subPath != null && subPath.length() > 0){
            path = File.separator +subPath;
        }
        String fileName = name + "-"+start+"-"+end+"-"+ System.currentTimeMillis() +".csv";
        File file = new File(path + File.separator + fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
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
           e.printStackTrace();
       }
    }

    @Override
    public void produce() {
        writeCsvHead();
        dealData();
        writeCsvContent();
        close();
    }

    public void dealData(){

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

    public String getSubPath() {
        return subPath;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }

    public List<T> getContents() {
        return contents;
    }

    public void setContents(List<T> contents) {
        this.contents = contents;
    }

}
