package com.servlet;

import com.dao.OrderListDao;
import com.dao.impl.OrderListDaoImpl;
import com.entity.OrderList;
import com.entity.PageBean;
import com.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: Iman
 * date   : 2019/3/29
 */
@WebServlet(name = "TypeIncomeServlet",urlPatterns = "/TypeIncomeServlet",initParams = {@WebInitParam(name = "pageCount",value = "10")})
public class TypeIncomeServlet extends HttpServlet {
    OrderListDao orderListDao=new OrderListDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String action = request.getParameter("action");
        if("query".equals(action)) {
            query(request, response);
        }
//        }else if("queryOne".equals(action)){
//            queryOne(request,response);
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
//    protected void queryOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String id = request.getParameter("kid");
//        String gradeId=null;
//        if(!StringUtil.isEmpty(id)){
//            gradeId=id;
//        }
//        OrderList orderList = orderListDao.findOrderListByOrderId(gradeId);
//
//        CarDiadeDao carDiadeDao=new CarDiadeDaoImpl();
//        CarDiade carDiade = carDiadeDao.findCarDiadeById(orderList.getGradeId());
//        orderList.setNetIncome(orderList.getAllCost(),carDiade.getCosts());
//        request.setAttribute("orderList",orderList);
//        request.setAttribute("carDiade",carDiade);
//        request.getRequestDispatcher("/background/account/typeIncome.jsp").forward(request,response);
//
//    }
    protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PageBean pageBean =  new PageBean();

        String pageIndex = request.getParameter("pageIndex");
        if(!StringUtil.isEmpty(pageIndex)){
            pageBean.setPageIndex(Integer.parseInt(pageIndex));
        }

        String pageCount = getInitParameter("pageCount");
        pageBean.setPageCount(Integer.parseInt(pageCount));

        pageBean.setCount(orderListDao.getOrderConut());

        List<OrderList> orderLists = orderListDao.queryTypePage(pageBean);


        request.setAttribute("orderLists",orderLists);
        request.setAttribute("pageBean",pageBean);
        request.getRequestDispatcher("/background/account/typeIncome.jsp").forward(request,response);

    }
}
