package cn.edu.scau.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Integer id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer num;

    private String image;

    private String catid;

    private Integer status;

    private Date created;

    private Date updated;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Product(Integer id, String name, String description, BigDecimal price, Integer num, String image, String catid, Integer status, Date created, Date updated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.num = num;
        this.image = image;
        this.catid = catid;
        this.status = status;
        this.created = created;
        this.updated = updated;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid == null ? null : catid.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}