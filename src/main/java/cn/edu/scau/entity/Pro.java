package cn.edu.scau.entity;

public class Pro {
    private Integer tag;
    private String name;
    private Integer id;
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

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pro(Integer tag, String name,Integer id,String description){
        this.name = name;
        this.tag = tag;
        this.id = id;
        this.description = description;
    }
    public Pro(){
        super();
    };
}
