package com.adtdata.neo4j.service.impl;

import com.adtdata.neo4j.dao.CompanyDao;
import com.adtdata.neo4j.domain.*;
import com.adtdata.neo4j.query.InvestParam;
import com.adtdata.neo4j.query.Param;
import com.adtdata.neo4j.service.CompanyService;
import com.adtdata.neo4j.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author aixiaobai
 * @date 2021/10/9 15:11
 */
@Service
public class CompanyServiceImpl implements CompanyService {

   @Resource
   private CompanyDao companyDao;

    @Override
    public List<Company> selectCompany(Param param) {
        List<Company> companies = companyDao.selectCompany(param);
        if(companies!= null){
            for (Company company : companies) {
                company.setCompanyName(StringUtil.delSpecialChar(company.getCompanyName()));
                company.setRegCapital(StringUtil.delSpecialChar(company.getRegCapital()));
            }
        }
        return companies;
    }

    @Override
    public long selectCompanyMaxId() {
        return companyDao.selectCompanyMaxId();
    }

    @Override
    public List<Person> selectPerson(Param param) {
        return companyDao.selectPerson(param);
    }

    @Override
    public List<ComAndPerRelation> selectComAndPerRelation(Param param) {
        return companyDao.selectComAndPerRelation(param);
    }

    @Override
    public List<ShareHolder> selectShareHolder(Param param) {
        return companyDao.selectShareHolder(param);
    }

    @Override
    public List<ShareHolder> selectSnShareHolder(Param param) {
        return companyDao.selectSnShareHolder(param);
    }

    @Override
    public List<Investment> selectInvestment(Param param) {
        return companyDao.selectInvestment(param);
    }

    @Override
    public List<Branch> selectBranch(Param param) {
        return companyDao.selectBranch(param);
    }

    @Override
    public List<KeyPerson> selectKeyPerson(Param param) {
        return companyDao.selectKeyPerson(param);
    }

    @Override
    public List<GroupPerson> selectGroupPerson(Param param) {
        return companyDao.selectGroupPerson(param);
    }

    @Override
    public List<ShareHolder> selectInvestmentById(String id) {
        return companyDao.selectInvestmentById(id);
    }

    @Override
    public List<String> selectInvestmentByIds(List<String> list) {
        return companyDao.selectInvestmentByIds(list);
    }

    @Override
    public List<Company> selectCompanyByIds(List<String> list) {
        return companyDao.selectCompanyByIds(list);
    }

    @Override
    public List<Company> selectCompanyByNames(List<String> list) {
        return companyDao.selectCompanyByNames(list);
    }

    @Override
    public int insertInvestRelation(List<InvestParam> list) {
        return 0;
    }

    @Override
    public int insertCompanyRelation(List<CompanyRelationModel> list) {
        return 0;
    }

    @Override
    public int insertCompanyShareRelation(List<CompanyRelationModel> list) {
        return 0;
    }

    @Override
    public List<Company> selectNoEntityCompany(Param param) {
        return companyDao.selectNoEntityCompany(param);
    }

    @Override
    public long selectNoEntityCompanyMaxId() {
        return companyDao.selectNoEntityCompanyMaxId();
    }
}
