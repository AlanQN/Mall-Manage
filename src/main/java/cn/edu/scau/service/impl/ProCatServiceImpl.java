package cn.edu.scau.service.impl;

import cn.edu.scau.component.Info;
import cn.edu.scau.dao.ProCatMapper;
import cn.edu.scau.dao.ProMapper;
import cn.edu.scau.entity.Pro;
import cn.edu.scau.entity.ProCat;
import cn.edu.scau.service.IProCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service
public class ProCatServiceImpl implements IProCatService{

    @Autowired
    ProCatMapper proCatMapper;

    @Autowired
    Info<ProCat> info;

    @Autowired
    Info<List<ProCat>> listInfo;

    @Override
    public Info<List<ProCat>> selectByTag(@RequestBody Map<String, Integer> request) {
        listInfo.clear();
        List<ProCat> procat = proCatMapper.selectByTag(request.get("tag"));
        if(!procat.isEmpty()){
            listInfo.setData(procat);
            listInfo.setSuccess("成功");
        }
        else {
            listInfo.setError("失败");
        }
        return listInfo;
    }

    @Override
    public Info<ProCat> insertBySome(@RequestBody ProCat record){
        info.clear();
        proCatMapper.insertBySome(record);
        info.setData(record);
        info.setSuccess("插入成功");
        return info;
    }
}
