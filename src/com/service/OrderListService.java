package com.service;

import com.dao.OrderListDao;
import com.dao.impl.OrderListDaoImpl;
import com.entity.OrderList;

import java.util.List;

public class OrderListService {
    private OrderListDao dao= new OrderListDaoImpl();
    public List<OrderList> findAll(){
        return  dao.findAll();
    }
    public int addOrder(OrderList orderList){
        return dao.addOrder(orderList);
    }
    public int updateOrder(OrderList orderList){
        return dao.updateOrder(orderList);
    }
    public int updateOverTime(OrderList orderList){
        return dao.updateOverTime(orderList);
    }
    public OrderList findOrder(){
        return dao.findOrder();
    }
    public List<OrderList> findAllOrder(int clientId){
        return dao.findAllOrder(clientId);
    }
}
