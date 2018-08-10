package cn.edu.scau.dto;

public class IndexInfo {

    private Integer productNum; //商品总数
    private Integer orderNum;   //订单总数
    private Integer userNum;    //用户总数
    private Integer viewNum;    //浏览量
    private String notice;  //公告

    public IndexInfo() {

    }

    public IndexInfo(Integer productNum, Integer orderNum, Integer userNum, Integer viewNum, String notice) {
        this.productNum = productNum;
        this.orderNum = orderNum;
        this.userNum = userNum;
        this.viewNum = viewNum;
        this.notice = notice;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
