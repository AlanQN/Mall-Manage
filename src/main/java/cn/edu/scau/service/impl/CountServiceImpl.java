package cn.edu.scau.service.impl;

import cn.edu.scau.dao.OrderMapper;
import cn.edu.scau.dto.Result;
import cn.edu.scau.entity.Count;
import cn.edu.scau.service.ICountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountServiceImpl implements ICountService {

//    @Autowired
//    CountMapper countMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    Result<List<Count>> listResult;

    @Override
    public Result<List<Count>> getDate(String start, String end) {
        List<Count> countList = orderMapper.listCount(start,end);
        if(countList != null){
            listResult.setSuccess(true);
            listResult.setData(countList);
            listResult.setError(null);
        }else {
            listResult.setSuccess(true);
            listResult.setData(null);
            listResult.setError("失败");
        }
        return listResult;
    }
}
