package cn.edu.scau.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Integer id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer num;

    private Integer tag;

    private Integer status;

    private Date create_time;

    private Date update_time;

    public Product(Integer id, String name, String description, BigDecimal price, Integer num, Integer tag, Integer status, Date create_time, Date update_time) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.num = num;
        this.tag = tag;
        this.status = status;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public Product() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}