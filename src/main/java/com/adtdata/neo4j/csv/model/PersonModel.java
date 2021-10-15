package com.adtdata.neo4j.csv.model;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.AbstractCsvProduce;
import com.adtdata.neo4j.domain.GroupPerson;

import java.io.IOException;
import java.util.List;

/**
 * @author aixiaobai
 * @date 2021/9/29 15:53
 */
public class PersonModel extends AbstractCsvProduce<GroupPerson> {

    public PersonModel(long start, long end, List<GroupPerson> contents) {
        super(start, end, LabelConstant.PERSON.getLabel(),  LabelConstant.PERSON.getType(), contents);
    }



    @Override
    public void writeCsvContent() {
        try {
            if(super.getContents() != null){
                for (GroupPerson content : super.getContents()) {
                    super.bos.write((
                            content.getPersonId()+"-"+content.getGroupId()+","
                            +content.getName()+","
                            +LabelConstant.PERSON.getLabel()+"\r\n").getBytes());
                }
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
