package cn.edu.scau.service.impl;

import cn.edu.scau.component.Info;
import cn.edu.scau.dao.ProMapper;
import cn.edu.scau.entity.Pro;
import cn.edu.scau.service.IProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.List;

@Service
public class ProServiceImpl implements IProService{

    @Autowired
    ProMapper proMapper;

    @Autowired
    Info<Pro> info;

    @Autowired
    Info<List<Pro>> listInfo;

    @Override
    public Info<List<Pro>> selectByTag(@RequestBody Map<String, Integer> request) {
            listInfo.clear();
            List<Pro> pro = proMapper.selectByTag(request.get("tag"));
            if(!pro.isEmpty()){
                listInfo.setData(pro);
                listInfo.setSuccess("成功");
            }
            else {
                listInfo.setError("失败");
            }
            return listInfo;
    }

    @Override
    public Info<Pro> insertBySome(@RequestBody Pro record){
        info.clear();
        proMapper.insertBySome(record);
        info.setData(record);
        info.setSuccess("插入成功");
        return info;
    }
}
