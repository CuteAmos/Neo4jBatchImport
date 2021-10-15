package com.adtdata.neo4j.csv.model;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.AbstractCsvProduce;
import com.adtdata.neo4j.domain.Company;

import java.io.IOException;
import java.util.List;

/**
 * @author aixiaobai
 * @date 2021/9/29 15:53
 */
public class CompanyModel extends AbstractCsvProduce<Company> {

    public CompanyModel(long start, long end, List<Company> contents) {
        super(start, end, LabelConstant.COMPANY.getLabel(),  LabelConstant.COMPANY.getType(), contents);
    }



    @Override
    public void writeCsvContent() {
        try {
            if(super.getContents() != null){
                for (Company content : super.getContents()) {
                    super.bos.write((
                            content.getId()+","
                            +content.getCompanyName()+","
                            +content.getNewComId()+","
                            +content.getRegCapital()+","
                            +content.getRegStatus()+","
                            +LabelConstant.COMPANY.getLabel()+"\r\n").getBytes());
                }
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
