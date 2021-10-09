package com.adtdata.neo4j.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ImportParam {


    @ApiModelProperty(value = "公司节点文件",name = "prefix",example = "company")
    private String prefix;

    @ApiModelProperty(value = "人节点文件",example = "person")
    private String prefix1;

    @ApiModelProperty(value = "关系节点文件",example = "comand")
    private String prefix2;

    @ApiModelProperty(value = "配置文件位置",example = "/data/neo4j/batch/batch-import-2.3/sample/batch.properties")
    private String properties;

    @ApiModelProperty(value = "数据库位置",example = "/data/neo4j/data/databases/graph.db")
    private String database;

    @ApiModelProperty(value = "CSV文件位置",example = "/data/neo4j/csv/file/")
    private String filePath;

    public String getPrefix2() {
        return prefix2;
    }

    public void setPrefix2(String prefix2) {
        this.prefix2 = prefix2;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }


    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix1() {
        return prefix1;
    }

    public void setPrefix1(String prefix1) {
        this.prefix1 = prefix1;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
