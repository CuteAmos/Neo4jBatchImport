package com.adtdata.neo4j.csv.model;

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
        super(start, end, "n-company", null, contents);
    }

    @Override
    public void writeCsvHead() {
        try {
            //super.bos.write("1:LABEL\tcid:STRING:cIndex\tname\tnewCid\tcapV\tstatus\r\n".getBytes());
            super.bos.write("cid:ID,name,newCid,capV,status,:LABEL\r\n".getBytes());
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
                    super.bos.write((content.getId()+","
                            +content.getCompanyName()+","
                            +content.getNewComId()+","
                            +content.getRegCapital()+","
                            +content.getRegStatus()+",company\r\n").getBytes());
                   /* super.bos.write(("company\t"
                            +content.getId()+"\t"
                            +content.getCompanyName()+"\t"
                            +content.getNewComId()+"\t"
                            +content.getRegCapital()+"\t"
                            +content.getRegStatus()+"\r\n").getBytes());*/
                }
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
