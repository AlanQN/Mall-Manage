package cn.edu.scau.entity;

public class Express {
    private Integer id;

    private String expressName;

    public Express(Integer id, String expressName) {
        this.id = id;
        this.expressName = expressName;
    }

    public Express() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName == null ? null : expressName.trim();
    }
}