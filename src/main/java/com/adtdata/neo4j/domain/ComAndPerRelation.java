package com.adtdata.neo4j.domain;

/**
 * 公司与人关系表
 */
public class ComAndPerRelation {

    private long id;

    private long companyId;

    private long personId;

    private int groupId;

    private int islegal;

    private int iskeyperson;

    private int isshareholder;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public int getIslegal() {
        return islegal;
    }

    public void setIslegal(int islegal) {
        this.islegal = islegal;
    }

    public int getIskeyperson() {
        return iskeyperson;
    }

    public void setIskeyperson(int iskeyperson) {
        this.iskeyperson = iskeyperson;
    }

    public int getIsshareholder() {
        return isshareholder;
    }

    public void setIsshareholder(int isshareholder) {
        this.isshareholder = isshareholder;
    }
}
