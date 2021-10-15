package com.adtdata.neo4j.csv.model;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.AbstractCsvProduce;
import com.adtdata.neo4j.domain.Branch;
import com.adtdata.neo4j.domain.ShareHolder;

import java.io.IOException;
import java.util.List;

/**
 * @author aixiaobai
 * @date 2021/9/29 15:53
 */
public class NSHModel extends AbstractCsvProduce<ShareHolder> {

    public NSHModel(long start, long end, List<ShareHolder> contents) {
        super(start, end, LabelConstant.NSH.getLabel(),  LabelConstant.NSH.getType(), contents);
    }



    @Override
    public void writeCsvContent() {
        try {
            if(super.getContents() != null){
                for (ShareHolder content : super.getContents()) {
                    super.bos.write((content.getCompanyId()+","
                            +content.getShareholderId()+","
                            +LabelConstant.NSH.getLabel()+","
                            +"1,"
                            +content.getCap()+","
                            +content.getCapital()+"\r\n").getBytes());
                }
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
