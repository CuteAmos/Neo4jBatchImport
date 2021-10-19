package com.adtdata.neo4j.utils;

/**
 * @author aixiaobai
 * @date 2021/10/15 17:29
 */
public class ImporterUtil {

    public static boolean isImportDone(String res){
        if(res != null){
            return res.contains("IMPORT DONE.");
        }
        return false;
    }


}
