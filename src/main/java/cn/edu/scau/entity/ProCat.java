package cn.edu.scau.entity;

public class ProCat {
    private Integer tag;
    private String name;
    private String description;

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ProCat(Integer tag, String name,String description){
        this.name = name;
        this.tag = tag;
        this.description = description;
    }
    public ProCat(){
        super();
    }
}
