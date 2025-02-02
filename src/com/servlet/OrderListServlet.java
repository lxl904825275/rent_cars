package com.servlet;

import com.dao.OrderListDao;
import com.dao.impl.OrderListDaoImpl;
import com.entity.OrderList;
import com.entity.PageBean;
import com.service.OrderListService;
import com.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderListServlet",urlPatterns = "/OrderListServlet",initParams = {@WebInitParam(name="pageCount",value="5")})
public class OrderListServlet extends HttpServlet {
    private OrderListDao dao = new OrderListDaoImpl();
    private OrderListService service = new OrderListService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        if("query".equals(action)){
            query(request,response);
        }else if("add".equals(action)){
            add(request,response);
        }else if("update".equals(action)){
            update(request,response);
        }else if("delete".equals(action)){
            delete(request,response);
        }else if("deleteAll".equals(action)){
            deleteAll(request,response);
        }else if("queryOne".equals(action)){
            queryOne(request,response);
        }else if("findOne".equals(action)){
            findOne(request,response);
        }else if("toOrderList".equals(action)){
            String orderListId = request.getParameter("kid");
            OrderList orderList = dao.queryOne(orderListId);
            request.setAttribute("orderList",orderList);
            request.getRequestDispatcher("/background/order/detal.jsp").forward(request,response);
        }else if("addd".equals(action)){
            addd(request,response);
        }else if("updat".equals(action)){
            updat(request,response);
        }else if("updateOverTime".equals(action)){
            updateOverTime(request,response);
        }else if("findOrder".equals(action)){
            findOrder(request,response);
        }else if("findAll".equals(action)){
            findAll(request,response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientId = request.getParameter("clientId");
        List<OrderList> allOrder = service.findAllOrder(Integer.parseInt(clientId));
        for (OrderList list:allOrder) {
            System.out.println(list);
        }
        request.setAttribute("allOrder",allOrder);
        request.getRequestDispatcher("/MyAllList.jsp").forward(request,response);

    }
    protected void findOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date date = new Date();
        String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
        OrderList orderList = service.findOrder();
        request.setAttribute("orderList",orderList);
        request.setAttribute("format",format);
        request.getRequestDispatcher("/newList.jsp").forward(request,response);
    }
    protected void updateOverTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date date = new Date();
        String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String orderId = request.getParameter("orderId");
        request.setAttribute("format",format);
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String carName = request.getParameter("carName");
        String lease = request.getParameter("lease");
        String orderTime = request.getParameter("orderTime");
        String overTime =null;
        String allCost = request.getParameter("allCost");
        overTime= format;
        OrderList orderList = new OrderList(orderId, name,tel, carName, lease, orderTime, overTime, Integer.parseInt(allCost));
        int i = service.updateOverTime(orderList);
        request.setAttribute("orderList",orderList);
        request.getSession().setAttribute("orderList",orderList);
        request.getRequestDispatcher("/newList.jsp").forward(request,response);
    }
    protected void updat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String carName = request.getParameter("carName");
        String lease = request.getParameter("lease");
        String orderTime = request.getParameter("orderTime");
        String overTime = request.getParameter("overTime");
        String allCost = request.getParameter("allCost");
        OrderList orderList = new OrderList(orderId, name,tel, carName, lease, orderTime, overTime, Integer.parseInt(allCost));
        int i = service.updateOrder(orderList);
        response.getWriter().print(i);
    }
    protected void addd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String orderId = request.getParameter("orderId");
        String orderId = request.getParameter("id");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String carName = request.getParameter("carName");
        String lease = request.getParameter("lease");
        String orderTime = request.getParameter("orderTime");
        //String overTime = request.getParameter("overTime");
        // String allCost = request.getParameter("allCost");
        OrderList orderList = new OrderList(orderId,name, tel, carName, lease, orderTime);
        /*OrderList orderList = new OrderList(orderId, name,tel, carName, lease, orderTime, overTime, Integer.parseInt(allCost));
        System.out.println("orderList========="+orderList);*/
        int i = service.addOrder(orderList);
        response.getWriter().print(i);
        request.getRequestDispatcher("/my.jsp").forward(request,response);
    }
    protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageBean pageBean =  new PageBean();
        // 页码
        String pageIndex = request.getParameter("pageIndex");
        if(!StringUtil.isEmpty(pageIndex)){
            pageBean.setPageIndex(Integer.parseInt(pageIndex));
        }
        // 每页条数
        String pageCount = getInitParameter("pageCount");
        pageBean.setPageCount(Integer.parseInt(pageCount));
        // 总条数
        pageBean.setCount(dao.getConut());
        // list数据
        List<OrderList> orderList = dao.queryPageOrderList(pageBean);
        request.setAttribute("orderList",orderList);
        request.setAttribute("pageBean",pageBean);
        System.out.println("===="+orderList);
        request.getRequestDispatcher("/background/order/orderList.jsp").forward(request,response);
    }
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String carName = request.getParameter("carName");
        String lease = request.getParameter("lease");
        String orderTime = request.getParameter("orderTime");
        String overTime = request.getParameter("overTime");
        String allCost = request.getParameter("allCost");
        OrderList orderList =  new OrderList(orderId,name,tel,carName,lease,orderTime,overTime,Integer.parseInt(allCost));

        int i = dao.addOrderList(orderList);

        response.getWriter().print(""+i);
    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String carName = request.getParameter("carName");
        String lease = request.getParameter("lease");
        String orderTime = request.getParameter("orderTime");
        String overTime = request.getParameter("overTime");
        String allCost = request.getParameter("allCost");
        OrderList link = new OrderList(orderId,name,tel,carName,lease,orderTime,overTime,Integer.parseInt(allCost));
        int i = dao.updateOrderList(link);
        response.getWriter().print(""+i);

    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String  orderId= id;
        int  i = dao.deleteOrderList(orderId);

        response.getWriter().print(""+i);
    }
    protected void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        String[] id = ids.split(",");
        int sum=0;
        for (String orderId:id) {
            int  i = dao.deleteOrderList(orderId);
            sum+=i;
        }
        response.getWriter().print(""+sum);
    }
    protected void queryOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String kid = request.getParameter("kid");

        String  orderId = kid;

        OrderList orderList = dao.queryOne(orderId);
        request.setAttribute("orderList",orderList);
        request.getRequestDispatcher("/background/order/orderupdate.jsp").forward(request,response);
    }
    protected void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String kid = request.getParameter("kid");
        String  orderId=kid;
        OrderList orderList = dao.queryOne(orderId);
        request.getSession().setAttribute("orderList",orderList);
        if (!orderId.isEmpty()){
            response.getWriter().print(1);
        }else{
            response.getWriter().print(0);
        }
        System.out.println("3"+orderList.toString());
    }

}
