package cn.edu.scau.service.impl;


import cn.edu.scau.dao.ProductMapper;
import cn.edu.scau.entity.Product;
import cn.edu.scau.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import cn.edu.scau.component.Info;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    ProductMapper productMapper;

    @Autowired
    private Info info;

    @Override
    public Info<Product> selectByPrimaryKey(@RequestBody Map<String, Integer> request) {
        Product product_id = productMapper.selectByPrimaryKey(request.get("id"));
        if(product_id != null){
            info.setData(product_id);
            info.getSuccess();
        }

        else {
            info.setError("查询商品不存在");
        }

        return info;
    }

    @Override
    public Info<Product> selectByPrice(@RequestBody Map<String,BigDecimal> requset) {
        List<Product> product_price = productMapper.selectByPrice(requset.get("price"));
        if(product_price != null){
            info.setData(product_price);
            info.getSuccess();
        }
        else {
            info.setError("查询商品不存在");
        }

        return info;
    }

    @Override
    public Info<Product> updateByPrimaryKeySelective(@RequestBody Product record) {
        if(productMapper.updateByPrimaryKeySelective(record) > 0 ){
           info.setData(record);
           info.getSuccess();
        }
        else {
            info.setError("更新失败");
        }
        return info;
    }



    @Override
    public Info<Product> insert(@RequestBody Product record) {

        if(productMapper.selectByPrimaryKey(record.getId()) == null){
            info.getSuccess();
            return info;
        }

        else{
            if (record.getId() != null) {
                info.setError("id值已存在，不可重复");
            }
            else if (record.getId() == null){
                info.setError("id为空");
            }
            else if (record.getCatid() == null){
                info.setError("分类为空");
            }
            else info.setError("其他错误");

            return info;
        }

    }


    @Override
    public Info<Product> deleteByPrimaryKey(@RequestBody Map<String, Integer> request) {
        Integer id = request.get("id");
        if(productMapper.deleteByPrimaryKey(id)>0){
            info.getSuccess();
        }
        else {
            info.setError("删除失败");
        }
        return info;
    }
}
