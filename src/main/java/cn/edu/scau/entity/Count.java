package cn.edu.scau.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Count {
    Date date;
    double count;

    public Count(Date date, double count) {
        this.date = date;
        this.count = count;
    }

    public Count() {
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getData() {
        return date;
    }

    public void setData(Date date) {
        this.date = date;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}
