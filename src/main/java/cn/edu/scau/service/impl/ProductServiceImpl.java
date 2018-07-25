package cn.edu.scau.service.impl;


import cn.edu.scau.dao.ProductMapper;
import cn.edu.scau.entity.Product;
import cn.edu.scau.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product selectByPrimaryKey(@RequestBody Map<String, Integer> request) {
//        获取id
        Integer id = request.get("id");
//        根据id查找商品信息
        Product product_id = productMapper.selectByPrimaryKey(id);
        return product_id;
    }

    @Override
    public List<Product> selectByPrice(@RequestBody Map<String,BigDecimal> requset) {
        BigDecimal price = requset.get("price");
        List<Product> product_price = productMapper.selectByPrice(price);
        return product_price;
    }

    @Override
    public Map<String,String> updateByPrimaryKeySelective(@RequestBody Product record) {
        Map<String ,String> response = new HashMap<String, String>();
        if(productMapper.updateByPrimaryKeySelective(record) > 0 ){
            response.put("message","success");
            return response;
        }
        else {
            response.put("message","failed");
        }
        return response;
    }



    @Override
    public Map<String, String> insert(Product record) {
        Map<String ,String> response = new HashMap<String, String>();
        if(productMapper.insert(record)>0){
            response.put("message","success");
            return response;
        }
        else {
            response.put("message","failed");
        }
        return response;
    }

    @Override
    public Map<String, String> deleteByPrimaryKey(Map<String, Integer> request) {
        Map<String ,String> response = new HashMap<String, String>();
        Integer id = request.get("id");
        if(productMapper.deleteByPrimaryKey(id)>0){
            response.put("message","success");
            return response;
        }
        else {
            response.put("message","failed");
        }
        return response;
    }
}
