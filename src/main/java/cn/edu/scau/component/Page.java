package cn.edu.scau.component;

import java.util.List;

public class Page<T> {

    private Integer pageNum;   //当前页号

    private Integer pageSize;   //一页中的记录数

    private Integer startIndex; //开始位置

    private Integer totalRecord;  //记录总数

    private Integer totalPage;  //总页数

    private Integer recordNum;  //当前页显示的记录个数

    private String keyword; //搜索关键字

    private Integer keyType;    //关键字类型

    private List<T> records;    //当前页显示的记录

    public Page() {

    }

    public Page(Integer pageNum, Integer pageSize, Integer startIndex, Integer totalRecord, Integer totalPage, Integer recordNum, String keyword, Integer keyType, List<T> records) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.startIndex = startIndex;
        this.totalRecord = totalRecord;
        this.totalPage = totalPage;
        this.recordNum = recordNum;
        this.keyword = keyword;
        this.keyType = keyType;
        this.records = records;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", startIndex=" + startIndex +
                ", totalRecord=" + totalRecord +
                ", totalPage=" + totalPage +
                ", recordNum=" + recordNum +
                ", keyword='" + keyword + '\'' +
                ", keyType=" + keyType +
                ", records=" + records +
                '}';
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(Integer recordNum) {
        this.recordNum = recordNum;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getKeyType() {
        return keyType;
    }

    public void setKeyType(Integer keyType) {
        this.keyType = keyType;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }
}
