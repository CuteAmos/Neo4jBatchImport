package com.adtdata.neo4j.utils;

import org.neo4j.batchimport.Importer;
import org.neo4j.batchimport.utils.Config;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author aixiaobai
 * @date 2021/10/8 18:33
 */
public class ImporterUtil {


    public static void importer(String[] args) throws IOException {
        final Config config = Config.convertArgumentsToConfig(args);
        File graphDb = new File(config.getGraphDbDirectory());
        Importer importer = new Importer(graphDb, config);
        Method doImport = null;
        try {
            doImport = importer.getClass().getDeclaredMethod("doImport");
            doImport.setAccessible(true);
            doImport.invoke(importer);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }




}
