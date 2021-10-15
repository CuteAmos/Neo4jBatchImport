package com.adtdata.neo4j.utils;

import com.adtdata.neo4j.config.ImporterConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author aixiaobai
 * @date 2021/10/11 10:27
 */
public class ShellUtil {

    private static String neo4jPath = ImporterConfig.getNeo4jPath();
    private static final boolean isWin = System.getProperty("os.name").toLowerCase().contains("win");
    private static final boolean isLinux = System.getProperty("os.name").toLowerCase().contains("linux");


    public static String execCMD(String cmd, String[] envp, File dir) {
        System.out.println(cmd);
        try {
            Process process = Runtime.getRuntime().exec(cmd, envp, dir);
            StringBuffer sb = new StringBuffer();

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                sb.append(line).append("\n");
            }
            process.waitFor();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed";
    }


    public static String importCsv(String database, Map<String, String> nodes, Map<String, String> relations) {
        StringBuilder sb = new StringBuilder();
        sb.append(getNeo4jAdminCmd());
        sb.append("import ");
        sb.append("--database=" + database + " ");
        if (nodes != null) {
            for (Map.Entry<String, String> entry : nodes.entrySet()) {
                sb.append("--nodes:" + entry.getKey() + "=");
                sb.append(entry.getValue() + " ");
            }
        }
        if (relations != null) {
            for (Map.Entry<String, String> entry : relations.entrySet()) {
                sb.append("--relationships:" + entry.getKey() + " ");
                sb.append(entry.getValue() + " ");
            }
        }
        sb.append("--ignore-duplicate-nodes=true --ignore-missing-nodes=true --multiline-fields=true ");
        return execCMD(sb.toString(), null, new File(neo4jPath));
    }

    public static String getNeo4jAdminCmd() {
        if (isWin) {
            return neo4jPath + File.separator + "bin" + File.separator + "neo4j-admin.bat ";
        } else {
            return neo4jPath + File.separator + "bin" + File.separator + "neo4j-admin ";
        }
    }

    public static String getNeo4jCmd() {
        if (isWin) {
            return neo4jPath + File.separator + "bin" + File.separator + "neo4j.bat ";
        } else {
            return neo4jPath + File.separator + "bin" + File.separator + "neo4j ";
        }
    }

    public static boolean isRunning() {
        String cmd = getNeo4jCmd();
        cmd += "status";
        String s = execCMD(cmd, null, new File(neo4jPath));
        return s.contains("Neo4j is running");
    }

    public static void startNeo4j() {
        String cmd = getNeo4jCmd();
        cmd += "start";
        execCMD(cmd, null, new File(neo4jPath));
    }

    public static void stopNeo4j() {
        String cmd = getNeo4jCmd();
        cmd += "stop";
        execCMD(cmd, null, new File(neo4jPath));
    }


}
