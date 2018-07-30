package cn.edu.scau.service.impl;

import cn.edu.scau.dao.ProductCatMapper;
import cn.edu.scau.entity.ProductCat;
import cn.edu.scau.service.IProductCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductCatServiceImpl implements IProductCatService{

    @Autowired
    private ProductCatMapper productCatMapper;

    @Override
    public ProductCat selectByPrimaryKey(Map<String, Integer> request) {
        Integer id = request.get("id");
        ProductCat product_id = productCatMapper.selectByPrimaryKey(id);
        return product_id;
    }

    @Override
    public Map<String, String> deleteByPrimaryKey(Map<String, Integer> request) {
        Map<String ,String> response = new HashMap<String, String>();
        Integer id = request.get("id");
        if(productCatMapper.deleteByPrimaryKey(id)>0){
            response.put("message","success");
            return response;
        }
        else {
            response.put("message","failed");
        }
        return response;
    }

    @Override
    public Map<String, String> insert(ProductCat record) {
        Map<String ,String> response = new HashMap<String, String>();
        Integer id = record.getId();
        Integer parent_id = record.getParentId();
        if(id >= parent_id) {
            if (productCatMapper.insert(record) > 0) {
                response.put("message", "success");
                return response;
            }
        }
        else {
            response.put("message","failed");
        }
        return response;
    }

    @Override
    public Map<String,String> updateByPrimaryKeySelective(@RequestBody ProductCat record) {
        Map<String ,String> response = new HashMap<String, String>();
        if(productCatMapper.updateByPrimaryKeySelective(record) > 0 ){
            response.put("message","success");
            return response;
        }
        else {
            response.put("message","failed");
        }
        return response;
    }

}

