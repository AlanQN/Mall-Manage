package cn.edu.scau.entity;

import java.util.List;

public class Page<T> {

    private Integer pageNum;   //当前页号

    private Integer pageSize;   //一页中的记录数

    private Integer startIndex; //开始位置

    private Integer totalRecord;  //记录总数

    private Integer totalPage;  //总页数

    private String keyword; //搜索关键字

    private List<T> records;    //当前页显示的记录

    public Page() {

    }

    public Page(Integer pageNum, Integer pageSize, Integer startIndex, Integer totalRecord, Integer totalPage, String keyword, List<T> records) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.startIndex = startIndex;
        this.totalRecord = totalRecord;
        this.totalPage = totalPage;
        this.keyword = keyword;
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
                ", keyword='" + keyword + '\'' +
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
