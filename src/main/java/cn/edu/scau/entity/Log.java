package cn.edu.scau.entity;

import java.util.Date;

public class Log {
    private Integer id;

    private String name;

    private Integer type;

    private String url;

    private String requestType;

    private String requestParam;

    private String user;

    private String ip;

    private Integer time;

    private Date createDate;

    public Log(Integer id, String name, Integer type, String url, String requestType, String requestParam, String user, String ip, Integer time, Date createDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
        this.requestType = requestType;
        this.requestParam = requestParam;
        this.user = user;
        this.ip = ip;
        this.time = time;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType == null ? null : requestType.trim();
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam == null ? null : requestParam.trim();
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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}