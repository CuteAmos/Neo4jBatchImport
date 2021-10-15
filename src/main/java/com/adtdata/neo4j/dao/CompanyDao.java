package com.adtdata.neo4j.dao;



import com.adtdata.neo4j.domain.*;
import com.adtdata.neo4j.query.InvestParam;
import com.adtdata.neo4j.query.Param;

import java.util.List;

public interface CompanyDao {

    List<Company> selectCompany(Param param);

    long selectCompanyMaxId();

    List<Person> selectPerson(Param param);

    long selectPersonMaxId();

    List<ComAndPerRelation> selectComAndPerRelation(Param param);

    long selectComAndPerRelationMaxId();

    List<ShareHolder> selectShareHolder(Param param);

    long selectShareHolderMaxId();

    List<ShareHolder> selectSnShareHolder(Param param);

    long selectSnShareHolderMaxId();

    List<Investment> selectInvestment(Param param);


    List<Branch> selectBranch(Param param);


    List<KeyPerson> selectKeyPerson(Param param);

    long selectKeyPersonMaxId();


    List<GroupPerson> selectGroupPerson(Param param);

    long selectGroupPersonMaxId();

    List<ShareHolder> selectInvestmentById(String id);

    List<String> selectInvestmentByIds(List<String> list);

    List<Company> selectCompanyByIds(List<String> list);

    List<Company> selectCompanyByNames(List<String> list);

    int insertInvestRelation(List<InvestParam> list);

    int insertCompanyRelation(List<CompanyRelationModel> list);

    int insertCompanyShareRelation(List<CompanyRelationModel> list);

    List<Company> selectNoEntityCompany(Param param);


    long selectNoEntityCompanyMaxId();

}
