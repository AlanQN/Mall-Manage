package cn.edu.scau.entity;

import java.math.BigDecimal;

public class OrderProduct {

    private Integer productId;

    private Integer orderId;

    private Integer num;

    private String name;

    private BigDecimal price;

    private BigDecimal totalFee;

    private String picPath;

    public OrderProduct( Integer productId, Integer orderId, Integer num, String name, BigDecimal price, BigDecimal totalFee, String picPath) {
        this.productId = productId;
        this.orderId = orderId;
        this.num = num;
        this.name = name;
        this.price = price;
        this.totalFee = totalFee;
        this.picPath = picPath;
    }

    public OrderProduct() {
        super();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }
}