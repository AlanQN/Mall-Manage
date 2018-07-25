package cn.edu.scau.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Log {

    private Integer id;

    private String name;

    private Integer type;

    private String user;

    private String ip;

    private Date createDate;

    public Log(Integer id, String name, Integer type, String user, String ip, Date createDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.user = user;
        this.ip = ip;
        this.createDate = createDate;
    }

    public Log() {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}