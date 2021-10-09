package com.adtdata.neo4j.vo;

/**
 * @author aixiaobai
 * @date 2021/10/8 11:33
 */
public class ResultVo {

    private String type;
    private long start;
    private String status;
    private long end;
    private long loadConut;


    public ResultVo() {
    }

    public ResultVo(String type, long start, long end, long loadConut,String status) {
        this.type = type;
        this.start = start;
        this.status = status;
        this.end = end;
        this.loadConut=loadConut;
    }


    public long getLoadConut() {
        return loadConut;
    }

    public void setLoadConut(long loadConut) {
        this.loadConut = loadConut;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "type='" + type + '\'' +
                ", start=" + start +
                ", status='" + status + '\'' +
                ", end=" + end +
                ", loadConut=" + loadConut +
                '}';
    }
}
