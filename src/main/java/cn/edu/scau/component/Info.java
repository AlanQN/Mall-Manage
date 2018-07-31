package cn.edu.scau.component;

import cn.edu.scau.dao.ProductMapper;
import cn.edu.scau.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import cn.edu.scau.dao.ProductMapper;
import org.springframework.stereotype.Controller;

@Component
public class Info<T> {

    private String success;
    private String error;
    private T data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void clear(){
        this.success = null;
        this.error = null;
        this.data = null;
    }
}
