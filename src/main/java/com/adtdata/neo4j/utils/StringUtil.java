package com.adtdata.neo4j.utils;

/**
 * @author aixiaobai
 * @date 2021/10/9 15:20
 */
public class StringUtil {

    public static String delSpecialChar(String str){
        if(str == null){
            return str;
        }
        return str.replaceAll("[,|\n|\r|\t]","").replace(" ","");
    }
}
