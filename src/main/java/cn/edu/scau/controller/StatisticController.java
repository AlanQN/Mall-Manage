package cn.edu.scau.controller;

import cn.edu.scau.component.Page;
import cn.edu.scau.dao.*;
import cn.edu.scau.dto.IndexInfo;
import cn.edu.scau.entity.Base;
import cn.edu.scau.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 统计
 */
@Controller
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private BaseMapper baseMapper;

    /**
     * 获取首页信息
     * @return
     */
    @RequestMapping(value = "/indexInfo", method = RequestMethod.GET)
    @ResponseBody
    public IndexInfo getIndexInfo() {
        //创建保存首页信息的对象
        IndexInfo indexInfo = new IndexInfo();
        //获取浏览量
        int viewNum = logMapper.getTotalViewNum();
        //获取商品总数
        int productNum = productMapper.getTotalProductNum();
        //获取订单总数
        int orderNum = orderMapper.getTotal(new Page<Order>());
        //获取用户总数
        int userNum = userMapper.getTotalUserNum();
        //获取公告
        Base base = null;
        List<Base> baseList = baseMapper.find();
        if (baseList != null && baseList.size() > 0) {
            base = baseList.get(0);
        }
        if (base != null) {
            indexInfo.setNotice(base.getNotice());
        }
        //设置首页信息
        indexInfo.setUserNum(userNum);
        indexInfo.setViewNum(viewNum);
        indexInfo.setOrderNum(orderNum);
        indexInfo.setProductNum(productNum);
        //返回
        return indexInfo;
    }

}
