package com.adtdata.neo4j.constants;

/**
 * @author aixiaobai
 * @date 2021/10/12 0:31
 */
public enum  LabelConstant {
    //COMPANY_NE
    COMPANY_NE(0,"node","company-ne"),
    //COMPANY
    COMPANY(1,"node","company"),
    PERSON(2,"node","person"),
    PERSON1(3,"node","person1"),
    GR(4,"relation","gr"),
    BR(5,"relation","br"),
    KP(6,"relation","kp"),
    NSH(7,"relation","nsh"),
    SH(8,"relation","sh"),
    NESH(9,"relation","nesh")
    ;

    private int code;
    private String type;
    private String label;

    LabelConstant(int code, String type, String label) {
        this.code = code;
        this.type = type;
        this.label = label;
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
}
