package com.adtdata.neo4j.query;

import io.swagger.annotations.ApiModelProperty;

public class Param {

    @ApiModelProperty(value = "起始ID",position = 1,example = "0")
    private long start;

    @ApiModelProperty(value = "结束ID",position = 2,example = "1000000")
    private long end;

    public Param() {
    }

    public Param(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
