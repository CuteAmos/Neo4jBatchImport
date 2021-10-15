package com.adtdata.neo4j.csv.model;

import com.adtdata.neo4j.constants.LabelConstant;
import com.adtdata.neo4j.csv.AbstractCsvProduce;
import com.adtdata.neo4j.domain.GroupPerson;
import com.adtdata.neo4j.domain.Person;

import java.io.IOException;
import java.util.List;

/**
 * @author aixiaobai
 * @date 2021/9/29 15:53
 */
public class Person1Model extends AbstractCsvProduce<Person> {

    public Person1Model(long start, long end, List<Person> contents) {
        super(start, end, LabelConstant.PERSON1.getLabel(),  LabelConstant.PERSON1.getType(), contents);
    }



    @Override
    public void writeCsvContent() {
        try {
            if(super.getContents() != null){
                for (Person content : super.getContents()) {
                    super.bos.write((
                            content.getId()+","
                            +content.getPersonName()+","
                            +LabelConstant.PERSON1.getLabel()+"\r\n").getBytes());
                }
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
