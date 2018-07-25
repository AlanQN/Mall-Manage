package cn.edu.scau.service.impl;

import cn.edu.scau.component.Page;
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
        String s = "%"+string+"%";
        System.out.println(s);
        List<Express> expressList = expressMapper.search(s);
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

    @Override
    public Page<Express> getPage(Page<Express> page) {
        //查询记录总数
        int total = expressMapper.getTotal(page);
        //设置记录总数和偏移量
        page.setTotalRecord(total);
        page.setTotalPage((int)Math.ceil((double)total/page.getPageSize()));
        page.setStartIndex((page.getPageNum()-1)*page.getPageSize());
        //查找记录
        if (page.getPageNum() > 0 && page.getPageNum() <= page.getTotalPage()) {
            List<Express> expressList = expressMapper.findRecords(page);
            page.setRecords(expressList);
            page.setRecordNum(expressList.size());
        }
        return page;
    }

    @Override
    public Map<String, Object> deleteMore(Integer[] ids) {
        Map<String,Object> map = new HashMap<>();
        //删除批量选定的订单
        if(expressMapper.deleteMore(ids) > 0){
            map.put("errorCode",0);
        }else {
            map.put("errorCode",1);
        }
        return map;
    }

}
