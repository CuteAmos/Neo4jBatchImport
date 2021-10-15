package com.adtdata.neo4j.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author aixiaobai
 * @date 2021/10/9 15:20
 */
public class StringUtil {

    public static String getMoney(String cap){
        String result = "0";
        if(!StringUtil.isEmpty(cap)){
            if(cap.contains("amomon")){
                BigDecimal money = new BigDecimal("0");
                String[] str = cap.split(",");
                for (int i=0;i<str.length;i++){
                    if(str[i].contains("amomon")){
                        StringBuffer sb = new StringBuffer();
                        String regEx="[^0-9.]";
                        Pattern p = Pattern.compile(regEx);
                        String[] str1 = str[i].split("");
                        for (int j = 0;j<str1.length;j++) {
                            Matcher m = p.matcher(str1[j]);
                            sb.append(m.replaceAll("").trim());
                        }
                        if(!StringUtil.isEmpty(sb.toString())) {
                            money = new BigDecimal(sb.toString()).add(money);
                        }
                    }
                }
                result = money.toString();
            }else {
                StringBuffer sb = new StringBuffer();
                String[] str =cap.split("");
                for (int i = 0;i<str.length;i++){
                    String regEx="[^0-9.]";
                    Pattern p = Pattern.compile(regEx);
                    Matcher m = p.matcher(str[i]);
                    sb.append(m.replaceAll("").trim());
                }
                try {
                    if(!StringUtil.isEmpty(sb.toString())) {
                        result = new BigDecimal(sb.toString()).toString();
                    }
                }catch (Exception e){

                }
            }
        }
        result = keepTwoDecimals(result);
        return result;
    }



    public static String getCapital(String str){
        try {
            String unit = AmounDismantlingUtil.getAmountDismantlingAmount(str,2);
            if(unit != null && unit.contains("[")){
                unit = "";
            }
            if(StringUtil.isEmpty(unit)){
                unit = "万元";
            }
            String cap = getMoney(str);
            return cap+unit;
        }catch (Exception e){
            return "";
        }
    }



    public static String keepTwoDecimals(String scale){
        BigDecimal big = new BigDecimal(Double.parseDouble(String.valueOf(scale))).setScale(2, BigDecimal.ROUND_HALF_UP);
        return big.toString();
    }

    public static String delSpecialChar(String str){
        if(str == null){
            return "";
        }
        return str.replaceAll("[,~`!@#$%^&*={}:;\"'?<>/\t\n\r]","");
    }

    public static String handleNull(String str){
        if(str == null){
            return "";
        }
        return str;
    }

    public static boolean isEmpty(String str){
        return str == null|| str.length() == 0;
    }


    public static void main(String[] args) {
        System.out.println(getCapital("[{\"amomon\":\"2700\",\"time\":\"2036-12-06\",\"paymet\":\"货币\"}]"));
    }
}
