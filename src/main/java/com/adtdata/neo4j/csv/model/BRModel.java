package com.adtdata.neo4j.csv.model;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.AbstractCsvProduce;
import com.adtdata.neo4j.domain.Branch;
import com.adtdata.neo4j.domain.ComAndPerRelation;

import java.io.IOException;
import java.util.List;

/**
 * @author aixiaobai
 * @date 2021/9/29 15:53
 */
public class BRModel extends AbstractCsvProduce<Branch> {

    public BRModel(long start, long end, List<Branch> contents) {
        super(start, end, LabelConstant.BR.getLabel(),  LabelConstant.BR.getType(), contents);
    }



    @Override
    public void writeCsvContent() {
        try {
            if(super.getContents() != null){
                for (Branch content : super.getContents()) {
                    super.bos.write((content.getCompanyId()+","
                            +content.getBranchId()+","
                            +LabelConstant.BR.getLabel()+","
                            +"0\r\n").getBytes());
                }
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
