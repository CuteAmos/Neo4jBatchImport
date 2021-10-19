package com.adtdata.neo4j.batch.monitor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author aixiaobai
 * @date 2021/10/18 11:47
 */
public class ProgressMonitor {
    private static Map<String ,Integer> progress = new ConcurrentHashMap<>();
    private static Map<String ,Integer> totalTask = new ConcurrentHashMap<>();

    private static boolean isImporterRunning = false;


    public static synchronized void increase(String label){
        if(progress.containsKey(label)){
            progress.put(label,progress.get(label) + 1);
        }else{
            progress.put(label,1);
        }
    }

    public static synchronized void init(String label,Integer value){
        totalTask.put(label,value);
        progress.put(label,0);
    }

    public static Map<String, Integer> getProgress() {
        return progress;
    }

    public static Map<String, Integer> getTotalTask() {
        return totalTask;
    }

    public static synchronized String getProgressStr() {
        StringBuffer sb = new StringBuffer();
        if(totalTask != null){
            for (Map.Entry<String, Integer> entry : totalTask.entrySet()) {
                if(entry.getValue() == null || entry.getValue() == 0){
                    sb.append(entry.getKey() + " [ "+"completed : "+0+" , total : "+" 0 , progress : 100% ] ; ");
                }else{
                    Integer pro = progress.get(entry.getKey());
                    if(pro == null) {
                        pro = 0;
                    }
                    sb.append(entry.getKey() + " [ "+"completed : "+pro+" , total : "+entry.getValue()+" , progress : "+(pro*100/entry.getValue())+"% ] ; ");
                }
            }
        }
        return sb.toString();
    }

    public static synchronized void clear(){
        totalTask.clear();
        progress.clear();
    }

    public static synchronized boolean isImporterRunning() {
        return isImporterRunning;
    }

    public static synchronized void setIsImporterRunning(boolean isImporterRunning) {
        ProgressMonitor.isImporterRunning = isImporterRunning;
    }
}
