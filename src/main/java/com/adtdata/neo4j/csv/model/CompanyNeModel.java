package com.adtdata.neo4j.csv.model;

import com.adtdata.neo4j.csv.AbstractCsvProduce;
import com.adtdata.neo4j.domain.Company;

import java.io.IOException;
import java.util.List;

/**
 * @author aixiaobai
 * @date 2021/9/29 15:53
 */
public class CompanyNeModel extends AbstractCsvProduce<Company> {

    public CompanyNeModel(long start, long end,List<Company> contents) {
        super(start, end, "n-companyne", null, contents);
    }

    @Override
    public void writeCsvHead() {
        try {
            super.bos.write("1:LABEL\tcid:STRING:cIndex\tname\r\n".getBytes());
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeCsvContent() {
        try {
            if(super.getContents() != null){
                for (Company content : super.getContents()) {
                    super.bos.write(("company-ne\t"+content.getId()+"\t"+content.getCompanyName()+"\r\n").getBytes());
                }
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
