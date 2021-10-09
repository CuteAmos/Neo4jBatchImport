package com.adtdata.neo4j.query;


public class InvestParam {

    private Long companyId;

    private String companyName;

    private String capitalactl;

    private String stakesRatio;

    private String code;

    private String nameLabel;

    private Long parentId;

    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(String nameLabel) {
        this.nameLabel = nameLabel;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCapitalactl() {
        return capitalactl;
    }

    public void setCapitalactl(String capitalactl) {
        this.capitalactl = capitalactl;
    }

    public String getStakesRatio() {
        return stakesRatio;
    }

    public void setStakesRatio(String stakesRatio) {
        this.stakesRatio = stakesRatio;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
