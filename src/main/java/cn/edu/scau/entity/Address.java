package cn.edu.scau.entity;

public class Address {
    private Integer addressId;

    private String userId;

    private String userName;

    private String tel;

    private String streetName;

    private Boolean isDefault;

    public Address(Integer addressId, String userId, String userName, String tel, String streetName, Boolean isDefault) {
        this.addressId = addressId;
        this.userId = userId;
        this.userName = userName;
        this.tel = tel;
        this.streetName = streetName;
        this.isDefault = isDefault;
    }

    public Address() {
        super();
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName == null ? null : streetName.trim();
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
}