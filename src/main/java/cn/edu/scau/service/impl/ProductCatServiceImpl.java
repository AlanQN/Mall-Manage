package cn.edu.scau.service.impl;

import cn.edu.scau.component.Info;
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

    @Autowired
    private Info info;

    @Override
    public Info<ProductCat> selectByPrimaryKey(Map<String, Integer> request) {
        info.clear();
        Integer id = request.get("id");
        ProductCat product_id = productCatMapper.selectByPrimaryKey(id);
        if(product_id != null){
            info.setData(product_id);
            info.setSuccess("查询成功");
        }

        else {
            info.setError("查询商品不存在");
        }
        return info;
    }

    @Override
    public Info<ProductCat> deleteByPrimaryKey(Map<String, Integer> request) {
        info.clear();
        Integer id = request.get("id");
        Integer del = productCatMapper.deleteByPrimaryKey(id);
        if(del > 0){
            info.setSuccess("删除成功");
            return info;
        }
        else {
            info.setError("删除失败");
            return info;
        }

    }

    @Override
    public Info<ProductCat> insert(ProductCat record) {
        info.clear();
        info.setData(record);
        info.setSuccess("查询成功");
        return info;
    }

    @Override
    public Info<ProductCat> updateByPrimaryKeySelective(@RequestBody ProductCat record) {
        info.clear();
        if(productCatMapper.updateByPrimaryKeySelective(record) > 0 ){
            info.setData(record);
            info.setSuccess("更新成功");
        }
        else {
            info.setError("更新失败");
        }
        return info;
    }

}

