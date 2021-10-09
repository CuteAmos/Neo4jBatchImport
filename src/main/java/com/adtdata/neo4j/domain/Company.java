package com.adtdata.neo4j.domain;

public class Company {

    private long id;

    private Long newComId;

    private String companyName;

    private String regCapital;

    private String regStatus;

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public Long getNewComId() {
        return newComId;
    }

    public void setNewComId(Long newComId) {
        this.newComId = newComId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
