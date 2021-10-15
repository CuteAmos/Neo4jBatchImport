package com.adtdata.neo4j.domain;

public class ShareHolder {

    private long id;

    private long companyId;

    private String companyName;

    private long shareholderId;

    private String investorType;

    private String amount;

    private String regCapital;

    private String capital;

    private Long newComId;

    private double cap;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getCap() {
        return cap;
    }

    public void setCap(double cap) {
        this.cap = cap;
    }

    public Long getNewComId() {
        return newComId;
    }

    public void setNewComId(Long newComId) {
        this.newComId = newComId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getShareholderId() {
        return shareholderId;
    }

    public void setShareholderId(long shareholderId) {
        this.shareholderId = shareholderId;
    }

    public String getInvestorType() {
        return investorType;
    }

    public void setInvestorType(String investorType) {
        this.investorType = investorType;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
