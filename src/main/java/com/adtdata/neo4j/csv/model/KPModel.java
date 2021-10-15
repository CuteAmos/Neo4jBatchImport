package com.adtdata.neo4j.csv.model;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.AbstractCsvProduce;
import com.adtdata.neo4j.domain.Branch;
import com.adtdata.neo4j.domain.KeyPerson;

import java.io.IOException;
import java.util.List;

/**
 * @author aixiaobai
 * @date 2021/9/29 15:53
 */
public class KPModel extends AbstractCsvProduce<KeyPerson> {

    public KPModel(long start, long end, List<KeyPerson> contents) {
        super(start, end, LabelConstant.KP.getLabel(),  LabelConstant.KP.getType(), contents);
    }



    @Override
    public void writeCsvContent() {
        try {
            if(super.getContents() != null){
                for (KeyPerson content : super.getContents()) {
                    super.bos.write((content.getCompanyId()+","
                            +content.getStaffId()+","
                            +LabelConstant.KP.getLabel()+","
                            +"1\r\n").getBytes());
                }
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
