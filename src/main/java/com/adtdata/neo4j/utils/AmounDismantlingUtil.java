package com.adtdata.neo4j.utils;

import org.parboiled.common.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmounDismantlingUtil {



    //获取amomon金钱
    public static String getAmountDismantlingAmount(String cap,int flag) {
        String result = "";
        try {
            if (!StringUtil.isEmpty(cap)) {
                if (cap.contains("amomon")) {
                    BigDecimal money = new BigDecimal(0);
                    String unit = "";
                    String[] str = cap.split(",");
                    for (int i = 0; i < str.length; i++) {
                        if (str[i].contains("amomon")) {
                            String regEx1 = "[^\u4e00-\u9fa5]";
                            Pattern p1 = Pattern.compile(regEx1);
                            Matcher m = p1.matcher(str[i]);
                            unit = m.replaceAll("");
                            break;
                        }
                    }
                    if (flag == 1) {
                        result = String.valueOf(money);
                    }
                    if (flag == 2) {
                        result = unit;
                    }
                } else {
                    StringBuffer sb1 = new StringBuffer();
                    StringBuffer sb2 = new StringBuffer();
                    String[] str = cap.split("");
                    for (int i = 0; i < str.length; i++) {
                        String regEx = "[^0-9.]";
                        Pattern p = Pattern.compile(regEx);
                        if (str[i].matches(regEx)) {
                            sb2.append(str[i]);
                        } else {
                            Matcher m = p.matcher(str[i]);
                            sb1.append(m.replaceAll("").trim());
                        }
                    }
                    if (flag == 1) {
                        result = sb1.toString();
                    }
                    if (flag == 2) {
                        result = sb2.toString();
                        if(!StringUtil.isEmpty(result)) {
                            result = result.replace(",", "");
                        }
                    }
                }
            }
        }catch (Exception e){

        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getAmountDismantlingAmount("[{\"amomon\":\"1000万人民币元\",\"time\":\"2014-01-07\",\"paymet\":\"货币\"}]", 2));
    }

}
