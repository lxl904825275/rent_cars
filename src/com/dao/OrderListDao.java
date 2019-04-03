package com.dao;

import com.entity.OrderList;
import com.entity.PageBean;
import java.util.List;

/**
 * @author: Iman
 * date   : 2019/3/28
 */
public interface OrderListDao {
    public List<OrderList> findAll();

    public OrderList findOrderListByOrderId(String orderId);

    public List<OrderList> queryPage(PageBean pageBean);

    public int getConut();

    public int getOrderConut();

    public List<OrderList> queryTypePage(PageBean pageBean);

    public String getGrade();

    //分页显示
    public List<OrderList> queryPageOrderList(PageBean pageBean);
    //添加订单
    public int addOrderList(OrderList orderList);
    //修改订单
    public int deleteOrderList(String orderId);
    //删除订单
    public int updateOrderList(OrderList orderList);
    //查询订单
    public OrderList queryOne(String orderId);

    public int addOrder(OrderList orderList);
    public int updateOrder(OrderList orderList);
    public List<OrderList> findAllOrder(int clientId);

    public OrderList findOrder();
    public int updateOverTime(OrderList orderList);
}
