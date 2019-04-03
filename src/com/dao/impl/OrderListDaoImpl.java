package com.dao.impl;

import com.dao.OrderListDao;
import com.entity.OrderList;
import com.entity.PageBean;
import com.framework.JdbcUtil;
import com.framework.JdbcUtils;
import com.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Iman
 * date   : 2019/3/29
 */
public class OrderListDaoImpl implements OrderListDao {

    private QueryRunner qr = new QueryRunner();
    private QueryRunner qr1 =  new QueryRunner(DataSourceUtil.getDruidDataSource());

    @Override
    public int addOrder(OrderList orderList) {
        String sql = "insert into order_list(order_id,name,tel,car_name,lease,order_time)values(?,?,?,?,?,?)";
        Connection conn = JdbcUtils.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,orderList.getOrderId());
            ps.setString(2,orderList.getName());
            ps.setString(3,orderList.getTel());
            ps.setString(4,orderList.getCarName());
            ps.setString(5,orderList.getLease());
            ps.setString(6,orderList.getOrderTime());
           /* ps.setDate(7,new java.sql.Date(orderList.getOrderTime().getTime()));
            ps.setDate(8,new java.sql.Date(orderList.getOverTime().getTime()));*/
            int i = ps.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateOrder(OrderList orderList) {

        String sql = "update order_list set name=?,tel=?,car_name=?," +
                "lease=?,order_time=?,over_time=?,all_cost=? where order_id=?";
        try {
            int i = qr.update(JdbcUtils.getConnection(), sql,orderList.getOrderId());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int updateOverTime(OrderList orderList) {
        String sql = "update order_list set over_time=? where order_id=?";
        System.out.println("sql:" +  sql);
        Connection conn = JdbcUtils.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            System.out.println("prepare:" +  orderList.getOverTime());
            System.out.println("prepare:" +  orderList.getOrderId());
            ps.setString(1,orderList.getOverTime());
            ps.setString(2,orderList.getOrderId());
            int i = ps.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
//        String sql = "update order_list set name=?,tel=?,car_name=?," +
//                "lease=?,order_time=?,over_time=?,all_cost=? where order_id=?";
      /*  try {
            int i = qr.update(JdbcUtils.getConnection(), sql, orderList.getOverTime(),orderList.getOrderId());
            System.out.println(i);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        /* try {
            int i = qr.update(JdbcUtils.getConnection(), sql,orderList.getName(),orderList.getTel(),
                    orderList.getCarName(),orderList.getLease(),orderList.getOrderTime(),
                    orderList.getOverTime(),orderList.getAllCost(),orderList.getOrderId());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public List<OrderList> findAllOrder(int clientId){
        String sql = "select * from order_list where client_id="+clientId;
        try {
            List<OrderList> lists = qr.execute(JdbcUtil.getConnection(), sql, new BeanHandler<>(OrderList.class), clientId);
            System.out.println(lists);
            return lists;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OrderList findOrder() {

        Connection conn = JdbcUtils.getConnection();
        String sql = "select * from order_list  where over_time is NULL order by order_time desc ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String orderId = rs.getString("order_id");
                String tel1 = rs.getString("tel");
                String carName = rs.getString("car_name");
                String lease = rs.getString("lease");
                String orderTime = rs.getString("order_time");
                String overTime = rs.getString("over_time");
                int allCost = rs.getInt("all_cost");
                OrderList orderList = new OrderList(orderId, tel1, carName, lease, orderTime, overTime, allCost);
                return orderList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderList> queryPageOrderList(PageBean pageBean) {
        List<OrderList> list = new ArrayList<>();

        String sql="select order_id,name,tel,car_name,lease,order_time,over_time,all_cost from order_list order by order_time limit ?,?";
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,pageBean.getIndex());
            ps.setInt(2,pageBean.getPageCount());
            rs = ps.executeQuery();
            while (rs.next()){
                String orderId = rs.getString("order_id");
                String name = rs.getString("name");
                String tel = rs.getString("tel");
                String carName = rs.getString("car_name");
                String lease = rs.getString("lease");
                String orderTime = rs.getString("order_time");
                String overTime = rs.getString("over_time");
                int allCost = rs.getInt("all_cost");
                OrderList orderList =  new OrderList(orderId,name,tel,carName,lease,orderTime,overTime,allCost);
                list.add(orderList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
                if(ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return list;
    }

    @Override
    public int addOrderList(OrderList orderList) {
        String sql="insert into order_list(order_id,name,tel,car_name,lease,order_time,over_time,all_cost) values (?,?,?,?,?,?,?,?)";
        try {
            int i = qr.update(JdbcUtils.getConnection(),sql,
                    orderList.getOrderId(), orderList.getName(), orderList.getTel(),orderList.getCarName(),
                    orderList.getLease(),orderList.getOrderTime(),orderList.getOverTime(),orderList.getAllCost());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteOrderList(String orderId) {
        String sql="delete from order_list where order_id = ?";
        try {
            int i = qr.update(JdbcUtils.getConnection(),sql,orderId);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateOrderList(OrderList orderList) {
        String sql="update order_list set name = ? ,tel = ? ,car_name = ? ,lease = ?,order_time = ?,over_time = ?,all_cost= ? where order_id=?";
        try {
            int i = qr.update(JdbcUtils.getConnection(),sql,
                    orderList.getName(), orderList.getTel(), orderList.getCarName(),orderList.getLease(),
                    orderList.getOrderTime(),orderList.getOverTime(),orderList.getAllCost(),orderList.getOrderId());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public OrderList queryOne(String orderId) {

        String sql="select * from order_list  where order_id = ?";
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1,orderId);
            rs = ps.executeQuery();
            while (rs.next()){
                orderId = rs.getString("order_id");
                String name = rs.getString("name");
                String tel = rs.getString("tel");
                String carName = rs.getString("car_name");
                String lease = rs.getString("lease");
                String orderTime = rs.getString("order_time");
                String overTime = rs.getString("over_time");
                int allCost = rs.getInt("all_cost");
                OrderList orderList =  new OrderList(orderId,name,tel,carName,lease,orderTime,overTime,allCost);
                return orderList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
                if(ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return null;
    }

    @Override
    public List<OrderList> findAll() {
        List<OrderList> list=new ArrayList<>();
        String sql="select * from order_list ";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                String order_id = rs.getString("order_id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String tel = rs.getString("tel");
                String car_name = rs.getString("car_name");
                String lease = rs.getString("lease");
                String order_time = rs.getString("order_time");
                String over_time = rs.getString("over_time");
                int all_cost = rs.getInt("all_cost");
                int client_id = rs.getInt("client_id");
                int grade_id = rs.getInt("grade_id");

                OrderList orderList = new OrderList(order_id,name,sex,tel,car_name,lease,order_time,over_time,all_cost,client_id,grade_id);
                list.add(orderList);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
                if(ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }

        return list;
    }

    @Override
    public OrderList findOrderListByOrderId(String orderId) {

        String sql="select * from order_list where order_id=?";
        OrderList orderList =  new OrderList();

        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1,orderId);
            rs = ps.executeQuery();
            while (rs.next()){

                orderList.setOrderId(rs.getString("order_id"));
                orderList.setName(rs.getString("name"));
                orderList.setSex(rs.getString("sex"));

                orderList.setTel(rs.getString("tel"));
                orderList.setCarName(rs.getString("car_name"));
                orderList.setLease(rs.getString("lease"));
                orderList.setOrderTime(rs.getString("order_time"));
                orderList.setOverTime(rs.getString("over_time"));
                orderList.setAllCost(rs.getInt("all_cost"));
                orderList.setClientId(rs.getInt("client_id"));
                orderList.setGradeId(rs.getInt("grade_id"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
                if(ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return orderList;
    }


    @Override
    public List<OrderList> queryPage(PageBean pageBean) {
        String sql="select g.*,o.* from car_grade g,order_list  o where o.grade_id=g.grade_id order by order_time desc limit ?,?";
        List<OrderList> list =  new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,pageBean.getIndex());
            ps.setInt(2,pageBean.getPageCount());
            rs = ps.executeQuery();
            while (rs.next()){
                OrderList orderList =  new OrderList();

                String order_id = rs.getString("order_id");

                String name = rs.getString("name");
                int all_cost = rs.getInt("all_cost");

                orderList.setCosts(rs.getInt("vv_tax"),rs.getInt("isn_p"),rs.getInt("main_c"),rs.getInt("fuel_c"),
                        rs.getInt("invoice_t"),rs.getInt("v_manager"));
                orderList.setNetIncome(all_cost,orderList.getCosts());
                OrderList orderList1 =  new OrderList(order_id,name,all_cost,orderList.getCosts(),orderList.getNetIncome());



                list.add(orderList1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
                if(ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return list;
    }

    @Override
    public List<OrderList> queryTypePage(PageBean pageBean) {
        String sql="select g.*,SUM(all_cost) sum,o.* ,COUNT(1) count  from car_grade g,order_list  o where o.grade_id=g.grade_id GROUP BY o.grade_id  limit ?,?";
        List<OrderList> list =  new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,pageBean.getIndex());
            ps.setInt(2,pageBean.getPageCount());
            rs = ps.executeQuery();
            while (rs.next()){
                OrderList orderList=new OrderList();

                int grade_id = rs.getInt("grade_id");

                String grade = rs.getString("grade");

                int count = rs.getInt("count");

                int sum = rs.getInt("sum");


                orderList.setCosts(rs.getInt("vv_tax"),rs.getInt("isn_p"),rs.getInt("main_c"),rs.getInt("fuel_c"),
                        rs.getInt("invoice_t"),rs.getInt("v_manager"));
                orderList.setNetIncome(sum,orderList.getCosts());
                OrderList orderList1 =  new OrderList(grade_id,grade,count,sum,orderList.getCosts(),orderList.getNetIncome());



                list.add(orderList1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
                if(ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return list;
    }

    @Override
    public String getGrade() {
        String sql="select g.grade,o.* from car_grade g,order_list  o where o.grade_id=g.grade_id  ";

        PreparedStatement ps=null;
        ResultSet rs = null;
        String grade=null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()){
//                OrderList orderList =  new OrderList();

                grade = rs.getString("grade");

                }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
                if(ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return grade;
    }

    @Override
    public int getConut() {

        String sql="select count(*) count from order_list ";
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int count = rs.getInt("count");
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
                if(ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return 0;
    }

    @Override
    public int getOrderConut() {
        String sql="select count(*) count FROM car_grade";
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int count = rs.getInt("count");
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
                if(ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return 0;
    }
}
