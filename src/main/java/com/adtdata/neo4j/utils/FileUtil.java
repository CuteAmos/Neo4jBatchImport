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

    public static boolean renameFile(String from, String to){
        File fromFile = new File(from);
        File toFile = new File(to);
        if(fromFile.exists()){
            if(toFile.exists()){
                if(toFile.isDirectory()){
                    deleteDir(toFile);
                }else{
                    deleteFile(toFile);
                }
            }
            return fromFile.renameTo(toFile);
        }else{
            return false;
        }
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
