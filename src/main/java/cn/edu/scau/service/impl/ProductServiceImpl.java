package cn.edu.scau.service.impl;


import cn.edu.scau.dao.ProductMapper;
import cn.edu.scau.entity.Product;
import cn.edu.scau.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import cn.edu.scau.component.Info;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private Info info;

    @Autowired
    private Info<List<Product>> listInfo;

    @Override
    public Info<List<Product>> selectByName(@RequestBody Map<String, String> request) {
        listInfo.clear();
        String name = request.get("name");

        List<Product> product_name = productMapper.selectByName(name);

        System.out.println("123");
        System.out.println(product_name);
        if(!product_name.isEmpty()){
            System.out.println(product_name);
            listInfo.setData(product_name);
            listInfo.setSuccess("查询成功");
            return listInfo;
        }

        else {
            listInfo.setError("查询商品不存在");
            return listInfo;
        }
    }

    @Override
    public Info<List<Product>> selectByPrice(@RequestBody Map<String,BigDecimal> requset) {
        listInfo.clear();
        List<Product> product_price = productMapper.selectByPrice(requset.get("price"));
        if(!product_price.isEmpty()){

            System.out.println(product_price);
            listInfo.setData(product_price);
            listInfo.setSuccess("查询成功");
        }
        else {
            listInfo.setError("查询商品不存在");
        }
        return listInfo;
    }

    @Override
    public Info<Product> updateByPrimaryKeySelective(@RequestBody Product record) {
        info.clear();
        if(productMapper.updateByPrimaryKeySelective(record) > 0 ){
           info.setData(record);
           info.setSuccess("更新成功");
        }
        else {
            info.setError("更新失败");
        }
        return info;
    }



    @Override
    public Info<Product> insert(@RequestBody Product record) {
        info.clear();
        productMapper.insert(record);
        info.setData(record);
        info.setSuccess("添加成功");
        return info;
//        if(productMapper.selectByPrimaryKey(record.getId()) == null){
//            info.setSuccess();
//            info.getSuccess();
//            info.setData(record);
//        }
//
//        else{
//            if (record.getId() != null) {
//                info.setError("id值已存在，不可重复");
//            }
//            else if (record.getId() == null){
//                info.setError("id为空");
//            }
//            else if (record.getCatid() == null){
//                info.setError("分类为空");
//            }
//            else info.setError("其他错误");
//        }
//        return info;

    }


    @Override
    public Info<Product> deleteByPrimaryKey(@RequestBody Map<String, Integer> request) {
        info.clear();
        Integer id = request.get("id");
        Integer del = productMapper.deleteByPrimaryKey(id);
        if(del > 0){
            info.setSuccess("删除成功");
        }
        else {
            info.setError("删除失败");
        }
        return info;
    }
}
