package cn.edu.scau.service.impl;

import cn.edu.scau.dao.BaseMapper;
import cn.edu.scau.entity.Base;
import cn.edu.scau.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaseServiceImpl implements IBaseService {

    @Autowired
    private BaseMapper baseMapper;

    /**
     * 获取系统的基本信息
     * @return
     */
    @Override
    public Base getBasicInfo() {
        Base base = null;
        //获取系统基本信息
        List<Base> baseList = baseMapper.find();
        if (baseList != null && baseList.size() > 0) {
            base = baseList.get(0);
        }
        return base;
    }

    /**
     * 更新系统的基本信息
     * @param base 系统基本信息
     * @return
     */
    @Override
    public Map<String, Object> modifyBasicInfo(Base base) {
        Map<String, Object> response = new HashMap<String, Object>();
        //更新系统基本设置信息
        if (baseMapper.updateById(base) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

}
