package cn.edu.scau.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 封装json对象，所有返回结果都是用它
 */
@Component
public class Result<T> {

    private boolean success;//是否成功的标志

    private T data;//成功时返回的数据

    private String error;//错误信息

    public Result(){

    }

    //成功时的构造方法
    public Result(Boolean success ,T data){
        this.success = success;
        this.data = data;
    }

    //失败时的构造方法
    public Result(Boolean success ,String error){
        this.success = success;
        this.error =error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
