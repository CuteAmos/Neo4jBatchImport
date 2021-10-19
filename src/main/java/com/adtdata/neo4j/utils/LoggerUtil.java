package com.adtdata.neo4j.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author aixiaobai
 * @date 2021/10/15 15:55
 */
public class LoggerUtil {


    public static Logger getLogger(String name){
        return LoggerFactory.getLogger(name);
    }


    public static Logger getErrorLogger(){
        return LoggerFactory.getLogger("errorConsole");
    }

    public static Logger getDebugLogger(){
        return LoggerFactory.getLogger("debugConsole");
    }
}
