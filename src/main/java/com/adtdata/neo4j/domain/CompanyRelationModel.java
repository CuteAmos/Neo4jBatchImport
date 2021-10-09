package com.adtdata.neo4j.domain;

public class CompanyRelationModel {

    private String providerCompanyCode;

    private Long providerCompanyId;

    private String providerCompanyName;

    private String customerCompanyCode;

    private Long customerCompanyId;

    private String customerCompanyName;

    private Integer havingRelation;

    private String relationPath;

    public String getProviderCompanyCode() {
        return providerCompanyCode;
    }

    public void setProviderCompanyCode(String providerCompanyCode) {
        this.providerCompanyCode = providerCompanyCode;
    }

    public Long getProviderCompanyId() {
        return providerCompanyId;
    }

    public void setProviderCompanyId(Long providerCompanyId) {
        this.providerCompanyId = providerCompanyId;
    }

    public String getProviderCompanyName() {
        return providerCompanyName;
    }

    public void setProviderCompanyName(String providerCompanyName) {
        this.providerCompanyName = providerCompanyName;
    }

    public String getCustomerCompanyCode() {
        return customerCompanyCode;
    }

    public void setCustomerCompanyCode(String customerCompanyCode) {
        this.customerCompanyCode = customerCompanyCode;
    }

    public Long getCustomerCompanyId() {
        return customerCompanyId;
    }

    public void setCustomerCompanyId(Long customerCompanyId) {
        this.customerCompanyId = customerCompanyId;
    }

    public String getCustomerCompanyName() {
        return customerCompanyName;
    }

    public void setCustomerCompanyName(String customerCompanyName) {
        this.customerCompanyName = customerCompanyName;
    }

    public Integer getHavingRelation() {
        return havingRelation;
    }

    public void setHavingRelation(Integer havingRelation) {
        this.havingRelation = havingRelation;
    }

    public String getRelationPath() {
        return relationPath;
    }

    public void setRelationPath(String relationPath) {
        this.relationPath = relationPath;
    }
}
