package cn.edu.scau.entity;

public class Base {
    private Integer id;

    private String webName;

    private String keyWord;

    private String description;

    private String copyright;

    private Integer hasLogNotice;

    private String logNotice;

    private Integer hasAllNotice;

    private String allNotice;

    private String notice;

    private String updateLog;

    public Base(Integer id, String webName, String keyWord, String description, String copyright, Integer hasLogNotice, String logNotice, Integer hasAllNotice, String allNotice, String notice, String updateLog) {
        this.id = id;
        this.webName = webName;
        this.keyWord = keyWord;
        this.description = description;
        this.copyright = copyright;
        this.hasLogNotice = hasLogNotice;
        this.logNotice = logNotice;
        this.hasAllNotice = hasAllNotice;
        this.allNotice = allNotice;
        this.notice = notice;
        this.updateLog = updateLog;
    }

    public Base() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName == null ? null : webName.trim();
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord == null ? null : keyWord.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright == null ? null : copyright.trim();
    }

    public Integer getHasLogNotice() {
        return hasLogNotice;
    }

    public void setHasLogNotice(Integer hasLogNotice) {
        this.hasLogNotice = hasLogNotice;
    }

    public String getLogNotice() {
        return logNotice;
    }

    public void setLogNotice(String logNotice) {
        this.logNotice = logNotice == null ? null : logNotice.trim();
    }

    public Integer getHasAllNotice() {
        return hasAllNotice;
    }

    public void setHasAllNotice(Integer hasAllNotice) {
        this.hasAllNotice = hasAllNotice;
    }

    public String getAllNotice() {
        return allNotice;
    }

    public void setAllNotice(String allNotice) {
        this.allNotice = allNotice == null ? null : allNotice.trim();
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    public String getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(String updateLog) {
        this.updateLog = updateLog == null ? null : updateLog.trim();
    }
}