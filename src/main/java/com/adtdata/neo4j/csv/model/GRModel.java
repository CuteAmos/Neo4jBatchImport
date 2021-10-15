package com.adtdata.neo4j.csv.model;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.AbstractCsvProduce;
import com.adtdata.neo4j.domain.ComAndPerRelation;
import com.adtdata.neo4j.domain.GroupPerson;

import java.io.IOException;
import java.util.List;

/**
 * @author aixiaobai
 * @date 2021/9/29 15:53
 */
public class GRModel extends AbstractCsvProduce<ComAndPerRelation> {

    public GRModel(long start, long end, List<ComAndPerRelation> contents) {
        super(start, end, LabelConstant.GR.getLabel(),  LabelConstant.GR.getType(), contents);
    }



    @Override
    public void writeCsvContent() {
        try {
            if(super.getContents() != null){
                for (ComAndPerRelation content : super.getContents()) {
                    super.bos.write((content.getCompanyId()+","
                            +content.getPersonId()+"-"+content.getGroupId()+","
                            +LabelConstant.GR.getLabel()+","
                            +"0,"
                            + content.getName()+"\r\n").getBytes());
                }
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
