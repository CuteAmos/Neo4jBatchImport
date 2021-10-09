package com.adtdata.neo4j.utils;

import java.io.File;

/**
 * @author aixiaobai
 * @date 2021/10/9 10:56
 */
public class FileUtil {


    public static boolean deleteFile(File file){
        return file.delete();
    }

    public static boolean deleteDir(File file){
        if(file.isDirectory()){
            for (File listFile : file.listFiles()) {
                if(listFile.isDirectory()){
                    deleteDir(listFile);
                }else{
                    deleteFile(listFile);
                }
            }
        }
        return file.delete();
    }

    public static void deleteFileByPrefix(String prefix,File file){
        if(file.isDirectory()){
            for (File listFile : file.listFiles()) {
                if(listFile.isDirectory()){
                    deleteFileByPrefix(prefix,listFile);
                }else if(listFile.getName().startsWith(prefix)){
                    listFile.delete();
                }
            }
        }else if(file.getName().startsWith(prefix)){
            file.delete();
        }
    }




}
