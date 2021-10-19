package com.adtdata.neo4j.constants;

import com.adtdata.neo4j.utils.LoggerUtil;
import org.slf4j.Logger;

/**
 * @author aixiaobai
 * @date 2021/10/12 0:31
 */
public enum  LabelConstant {
    //COMPANY_NE
    COMPANY_NE(0,"node","company-ne","companyne"),
    //COMPANY
    COMPANY(1,"node","company","company"),
    PERSON(2,"node","person","person"),
    PERSON1(3,"node","person1","person1"),
    GR(4,"relation","gr","companygroup"),
    BR(5,"relation","br","branch"),
    KP(6,"relation","kp","keyperson"),
    NSH(7,"relation","nsh","nshare"),
    SH(8,"relation","sh","share"),
    NESH(9,"relation","nesh","share")
    ;

    private int code;
    private String type;
    private String label;
    private String loggerName;

    LabelConstant(int code, String type, String label,String logger) {
        this.code = code;
        this.type = type;
        this.label = label;
        this.loggerName = logger;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public String getTypeAndLabel() {
        return type+"_"+label;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public Logger getLogger(){
        return LoggerUtil.getLogger(loggerName);
    }




}
