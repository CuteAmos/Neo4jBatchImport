package com.adtdata.neo4j.domain;

public class Investment {

    private long id;

    private long companyId;

    private long investCompanyId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getInvestCompanyId() {
        return investCompanyId;
    }

    public void setInvestCompanyId(long investCompanyId) {
        this.investCompanyId = investCompanyId;
    }
}
