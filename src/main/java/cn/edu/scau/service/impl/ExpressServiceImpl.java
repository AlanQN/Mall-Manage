package cn.edu.scau.service.impl;

import cn.edu.scau.dao.ExpressMapper;
import cn.edu.scau.entity.Express;
import cn.edu.scau.service.IExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpressServiceImpl implements IExpressService {

    @Autowired
    private ExpressMapper expressMapper;

    @Override
    public Map<String, Object> insert(String name) {
        Map<String, Object> map = new HashMap<>();
        Integer errorCode = 0;
        if (expressMapper.selectByName(name) == null) {
            expressMapper.insert(name);
        } else {
            //存在相同的
            errorCode = 1;
        }
        map.put("errorCode", errorCode);
        return map;
    }

    @Override
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Integer errorCode = 0;
        if (expressMapper.deleteByPrimaryKey(id) == 0) {
            errorCode = 1;
        }
        map.put("errorCode", errorCode);
        return map;
    }

    @Override
    public Map<String, Object> get(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Integer errorCode = 0;
        Express express = expressMapper.selectByPrimaryKey(id);
        if (express == null) {
            errorCode = 1;
            map.put("errorCode", errorCode);
        } else {
            map.put("errorCode", errorCode);
            map.put("exprees", express);
        }
        return map;
    }

    @Override
    public Map<String, Object> update(Express express) {
        Map<String, Object> map = new HashMap<>();
        Integer errorCode = 0;
        if (expressMapper.selectByName(express.getExpressName()) != null || expressMapper.updateByPrimaryKey(express) == 0 ) {
            errorCode = 1;
        }
        map.put("errorCode", errorCode);
        return map;
    }

    @Override
    public Map<String, Object> search(String string) {
        Map<String, Object> map = new HashMap<>();
        Integer errorCode = 0;
        List<Express> expressList = expressMapper.search("%"+string+"%");
        if (expressList == null) {
            errorCode = 1;
            map.put("errorCode", errorCode);
        }else {
            map.put("errorCode", errorCode);
            map.put("expressList",expressList);
        }
        return map;
    }

    @Override
    public Map<String, Object> getAll() {
        Map<String, Object> map = new HashMap<>();
        Integer errorCode = 0;
        List<Express> expressList = expressMapper.getAll();
        if (expressList == null) {
            errorCode = 1;
            map.put("errorCode", errorCode);
        }else {
            map.put("errorCode", errorCode);
            map.put("expressList",expressList);
        }
        return map;
    }

}
