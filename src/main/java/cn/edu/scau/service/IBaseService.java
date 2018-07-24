package cn.edu.scau.service;

import cn.edu.scau.entity.Base;

import java.util.Map;

public interface IBaseService {

    /**
     * 获取系统的基本信息
     * @return
     */
    public Base getBasicInfo();

    /**
     * 更新系统的基本信息
     * @param base 系统基本信息
     * @return
     */
    public Map<String, Object> modifyBasicInfo(Base base);

}
