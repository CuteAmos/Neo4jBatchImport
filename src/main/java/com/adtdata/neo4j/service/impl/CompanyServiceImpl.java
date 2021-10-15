package com.adtdata.neo4j.service.impl;

import com.adtdata.neo4j.dao.CompanyDao;
import com.adtdata.neo4j.domain.*;
import com.adtdata.neo4j.query.InvestParam;
import com.adtdata.neo4j.query.Param;
import com.adtdata.neo4j.service.CompanyService;
import com.adtdata.neo4j.utils.RedisUtil;
import com.adtdata.neo4j.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author aixiaobai
 * @date 2021/10/9 15:11
 */
@Service
public class CompanyServiceImpl implements CompanyService {

   @Resource
   private CompanyDao companyDao;

   @Autowired
   private RedisUtil redisUtil;

   private static final String COM_CAP = "COM_CAP_";
   private static final String NO_ENTITY_KEY="NO_ENTITY_KEY_";

   private static final DecimalFormat fnum = new DecimalFormat("##0.00");



    @Override
    public List<Company> selectCompany(Param param) {
        List<Company> companies = companyDao.selectCompany(param);
        if(companies!= null){
            for (Company company : companies) {
                company.setCompanyName(StringUtil.delSpecialChar(company.getCompanyName()));
                company.setRegCapital(StringUtil.delSpecialChar(company.getRegCapital()));
                company.setRegStatus(StringUtil.delSpecialChar(company.getRegStatus()));
                company.setNewComId(company.getNewComId() == null ? 0:company.getNewComId());
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
        List<Person> people = companyDao.selectPerson(param);
        if (people != null) {
            for (Person person : people) {
                person.setPersonName(StringUtil.delSpecialChar(person.getPersonName()));
            }
        }
        return people;
    }

    @Override
    public long selectPersonMaxId() {
        return companyDao.selectCompanyMaxId();
    }

    @Override
    public List<ComAndPerRelation> selectComAndPerRelation(Param param) {
        List<ComAndPerRelation> relations = companyDao.selectComAndPerRelation(param);
        if (relations != null) {
            for (ComAndPerRelation relation : relations) {
                StringBuilder sb = new StringBuilder();
                if(relation.getIsshareholder() == 1){
                    sb.append("ns/");
                }
                if(relation.getIskeyperson() == 1){
                    sb.append("kp/");
                }
                if(relation.getIslegal() == 1){
                    sb.append("lp/");
                }
                String name = sb.toString();
                if(name != null && name.length() > 1){
                    relation.setName(name.substring(0,name.length() - 1));
                }else{
                    relation.setName("");
                }
            }
        }
        return relations;
    }

    @Override
    public long selectComAndPerRelationMaxId() {
        return companyDao.selectComAndPerRelationMaxId();
    }

    @Override
    public List<ShareHolder> selectShareHolder(Param param) {
        List<ShareHolder> shareHolders = companyDao.selectShareHolder(param);
        if(shareHolders != null){
            for (ShareHolder shareHolder : shareHolders) {
                handleShareholder(shareHolder);
                if(redisUtil.get(NO_ENTITY_KEY+shareHolder.getCompanyId())!= null
                        || redisUtil.get(NO_ENTITY_KEY+shareHolder.getShareholderId()) != null){
                    shareHolder.setType(2);
                }else{
                    shareHolder.setType(1);
                }
            }
        }

        return shareHolders;
    }

    @Override
    public long selectShareHolderMaxId() {
        return companyDao.selectShareHolderMaxId();
    }

    @Override
    public List<ShareHolder> selectSnShareHolder(Param param) {
        List<ShareHolder> shareHolders = companyDao.selectSnShareHolder(param);
        if (shareHolders != null) {
            for (ShareHolder shareHolder : shareHolders) {
               handleShareholder(shareHolder);
            }
        }
        return shareHolders;
    }

    @Override
    public long selectSnShareHolderMaxId() {
        return companyDao.selectSnShareHolderMaxId();
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
    public long selectKeyPersonMaxId() {
        return companyDao.selectKeyPersonMaxId();
    }

    @Override
    public List<GroupPerson> selectGroupPerson(Param param) {
        List<GroupPerson> groupPeople = companyDao.selectGroupPerson(param);
        for (GroupPerson groupPerson : groupPeople) {
            groupPerson.setName(StringUtil.delSpecialChar(groupPerson.getName()));
        }
        return groupPeople;
    }

    @Override
    public long selectGroupPersonMaxId() {
        return companyDao.selectGroupPersonMaxId();
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
        List<Company> companies = companyDao.selectNoEntityCompany(param);
        if(companies != null){
            for (Company company : companies) {
                company.setCompanyName(StringUtil.delSpecialChar(company.getCompanyName()));
                redisUtil.set(NO_ENTITY_KEY+company.getId(),"1");
            }
        }
        return companies;
    }

    @Override
    public long selectNoEntityCompanyMaxId() {
        return companyDao.selectNoEntityCompanyMaxId();
    }


    private void handleShareholder(ShareHolder shareHolder){
        String capStr = redisUtil.get(COM_CAP + shareHolder.getCompanyId());
        String amount = shareHolder.getAmount();
        capStr = StringUtil.getMoney(capStr);
        double cap = 0;
        try {
            if (!StringUtil.isEmpty(capStr) && !StringUtil.isEmpty(amount)) {
                if(!"0.0000".equals(amount) && !"0.00".equals(capStr)){
                    cap = (Double.parseDouble(amount) / Double.parseDouble(capStr));
                    cap = Double.parseDouble(fnum.format(cap));
                }
            }
        }catch (Exception e){

        }
        shareHolder.setCap(cap);
        shareHolder.setCapital(StringUtil.getCapital(shareHolder.getCapital()));
    }
}
