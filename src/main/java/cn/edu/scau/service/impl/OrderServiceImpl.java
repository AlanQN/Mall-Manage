package cn.edu.scau.service.impl;


import cn.edu.scau.component.Page;
import cn.edu.scau.dao.OrderMapper;
import cn.edu.scau.dao.OrderProductMapper;
import cn.edu.scau.dao.OrderShippingMapper;
import cn.edu.scau.dto.OrderInfo;
import cn.edu.scau.dto.Result;
import cn.edu.scau.entity.Order;
import cn.edu.scau.entity.OrderProduct;
import cn.edu.scau.entity.OrderShipping;
import cn.edu.scau.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderShippingMapper orderShippingMapper;
    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private Result<Order> orderFindResult;
    @Autowired
    private Result<OrderInfo> orderInfoResult;
    @Autowired
    private OrderInfo orderInfo;
    @Autowired
    private Result<String> orderStringResult;
    @Autowired
    private Result<Page<Order>> orderListResult;
    @Autowired
    private Page<Order> orderPage;


    /**
     * 通过id获取订单
     *
     * @param id
     * @return
     */
    @Override

    public Result<Order> getById(Integer id) {

        Order order = orderMapper.getById(id);
        if (order != null) {
            orderFindResult.setSuccess(true);
            orderFindResult.setData(order);
            orderFindResult.setError(null);
        } else {
            orderFindResult.setSuccess(false);
            orderFindResult.setError("不存在");
            orderFindResult.setData(null);
        }
        return orderFindResult;
    }

    /**
     * 获取订单详情
     * @param offset 起始位置
     * @param limit 数量
     * @param id 订单id
     * @return
     */
    @Override
    public Result<OrderInfo> getOrderInfoById(Integer offset, Integer limit, Integer id) {

        OrderShipping orderShipping = orderShippingMapper.select(id);
        List<OrderProduct> orderProductList = orderProductMapper.queryAllByID(offset, limit, id);
        Order order = orderMapper.getById(id);
        if ( orderProductList == null ) {
            orderInfoResult.setSuccess(false);
            orderInfoResult.setData(null);
            orderInfoResult.setError("无法得到订单详情或者订单商品");
        }
        else {
            orderInfo.setOrderProductList(orderProductList);
            orderInfo.setOrderShipping(orderShipping);
            orderInfo.setOrder(order);
            orderInfoResult.setSuccess(true);
            orderInfoResult.setData(orderInfo);
            orderInfoResult.setError(null);
        }
        return orderInfoResult;
    }

    /**
     * 批量删除订单
     * @param ids id数组
     * @return
     */
    @Override
    public Result<String> delete(Integer[] ids) {
        int i = orderMapper.delete(ids);
        if(i >0){
            orderStringResult.setSuccess(true);
            orderStringResult.setData("成功删除"+i+"个订单");
            orderStringResult.setError(null);
        }else {
            orderStringResult.setSuccess(false);
            orderStringResult.setData(null);
            orderStringResult.setError("删除失败");
        }
        return orderStringResult;
    }

    /**
     * 添加或修改备注
     * @param id 订单id
     * @param description 备注
     * @return
     */
    @Override
    public Result<String> description(Integer id, String description) {
        if(orderMapper.description(id,description) > 0){
            orderStringResult.setSuccess(true);
            orderStringResult.setData("成功");
            orderStringResult.setError(null);
        }else {
            orderStringResult.setSuccess(false);
            orderStringResult.setData(null);
            orderStringResult.setError("删除失败");
        }

        return orderStringResult;
    }

    /**
     * 取消订单
     * 只有状态为1和2的才能取消
     * @param id 订单id
     * @return
     */
    @Override
    public Result<String> cancel(Integer id) {
        if(orderMapper.cancel(id) > 0){
            orderStringResult.setSuccess(true);
            orderStringResult.setData("成功");
            orderStringResult.setError(null);
        }else {
            orderStringResult.setSuccess(false);
            orderStringResult.setData(null);
            orderStringResult.setError("取消失败");
        }
        return orderStringResult;
    }

    /**
     * 发货
     * @param orderId 订单id
     * @param shippingId 物流单号
     * @param shippingName 物流名称
     * @return
     */
    @Override
    public Result<String> consign(Integer orderId, String shippingId, String shippingName) {
        if(orderMapper.consign(orderId,shippingId,shippingName) > 0){
            orderStringResult.setSuccess(true);
            orderStringResult.setData("成功");
            orderStringResult.setError(null);
        }else {
            orderStringResult.setSuccess(false);
            orderStringResult.setData(null);
            orderStringResult.setError("发货失败");
        }
        return orderStringResult;
    }

    /**
     * 获取订单
     * 分页
     * @param pageNum 页
     * @param pageSize 数量
     * @return
     */
    @Override
    public Result<Page<Order>> orderList(Integer pageNum, Integer pageSize) {
        orderPage.setPageNum(pageNum);
        orderPage.setPageSize(pageSize);
        orderPage.setKeyword(null);
        //查询订单总数
        Integer totalNum = orderMapper.getTotal(orderPage);
        orderPage.setTotalRecord(totalNum);
        //设置总页数和偏移量
        Integer totalPage = (int) (Math.ceil((double) totalNum / pageSize));
        Integer startIndex =pageSize * (pageNum - 1);

        orderPage.setTotalPage(totalPage);
        orderPage.setStartIndex(startIndex);
        //查询当前页用户
        List<Order> orderList = orderMapper.findRecords(orderPage);

        if(orderList != null && orderList.size() > 0 ){
            orderListResult.setSuccess(true);
            orderPage.setRecordNum(orderList.size());
            orderPage.setRecords(orderList);
            orderListResult.setData(orderPage);
            orderListResult.setError(null);
        }else {
            orderListResult.setSuccess(false);
            orderListResult.setData(null);
            orderListResult.setError("获取失败");

        }
        return orderListResult;
    }

    /**
     * 搜索
     * @param pageNum 页
     * @param pageSize 数量
     * @param key 关键字
     * @return
     */
    @Override
    public Result<Page<Order>> search(Integer pageNum, Integer pageSize, String key) {
        orderPage.setPageNum(pageNum);
        orderPage.setPageSize(pageSize);
        orderPage.setKeyword(key);
        //查询订单总数
        Integer totalNum = orderMapper.getTotal(orderPage);
        orderPage.setTotalRecord(totalNum);
        //设置总页数和偏移量
        Integer totalPage = (int) Math.ceil((double) totalNum / pageSize);
        Integer startIndex =pageSize * (pageNum - 1);
        orderPage.setTotalPage(totalPage);
        orderPage.setStartIndex(startIndex);
        //查询当前页用户
        List<Order> orderList = orderMapper.findRecords(orderPage);

        if(orderList != null && orderList.size() > 0 ){
            orderListResult.setSuccess(true);
            orderPage.setRecordNum(orderList.size());
            orderPage.setRecords(orderList);
            orderListResult.setData(orderPage);
            orderListResult.setError(null);
        }else {
            orderListResult.setSuccess(false);
            orderListResult.setData(null);
            orderListResult.setError("获取失败");

        }
        return orderListResult;
    }

    @Override
    public Result<String> add(Order order) {
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        if(orderMapper.save(order) > 0){
            orderStringResult.setSuccess(true);
            orderStringResult.setData("成功");
            orderStringResult.setError(null);
        }else {
            orderStringResult.setSuccess(false);
            orderStringResult.setData(null);
            orderStringResult.setError("添加失败");
        }
        return orderStringResult;
    }


}
