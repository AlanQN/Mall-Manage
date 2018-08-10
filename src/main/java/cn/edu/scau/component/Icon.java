package cn.edu.scau.component;

public class Icon {

    private String oldUrl;
    private String file;
    private String id;    //上传者编号

    public Icon() {

    }

    public Icon(String oldUrl, String file, String id) {
        this.oldUrl = oldUrl;
        this.file = file;
        this.id = id;
    }

    @Override
    public String toString() {
        return "FileDomain{" +
                "oldUrl='" + oldUrl + '\'' +
                ", file=" + file +
                ", id='" + id + '\'' +
                '}';
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getOldUrl() {
        return oldUrl;
    }

    public void setOldUrl(String oldUrl) {
        this.oldUrl = oldUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
