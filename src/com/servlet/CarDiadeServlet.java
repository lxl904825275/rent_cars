package com.servlet;

import com.dao.CarDiadeDao;
import com.dao.impl.CarDiadeDaoImpl;
import com.entity.CarDiade;
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
 * date   : 2019/3/28
 */
@WebServlet(name = "CarDiadeServlet",urlPatterns = "/CarDiadeServlet",initParams = {@WebInitParam(name = "pageCount",value = "5")})
public class CarDiadeServlet extends HttpServlet {
    CarDiadeDao dao=new CarDiadeDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
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
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gradeId = request.getParameter("gradeId");
        String grade = request.getParameter("grade");
        String vvTax = request.getParameter("vvTax");
        String isnP = request.getParameter("isnP");
        String mainC = request.getParameter("mainC");
        String fuelC = request.getParameter("fuelC");
        String invoiceT = request.getParameter("invoiceT");
        String vManager = request.getParameter("vManager");

        CarDiade carDiade= new CarDiade(Integer.parseInt(gradeId),grade,Integer.parseInt(vvTax),Integer.parseInt(isnP),Integer.parseInt(mainC),Integer.parseInt(fuelC),Integer.parseInt(invoiceT),Integer.parseInt(vManager));
        int i = dao.updateCarDiade(carDiade);
        response.getWriter().print(""+i);

    }
    protected void queryOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("kid");
        int gradeId=-1;
        if(!StringUtil.isEmpty(id)){
            gradeId=Integer.parseInt(id);
        }
        CarDiade carDiades = dao.queryOne(gradeId);
        request.setAttribute("carDiades",carDiades);
        request.getRequestDispatcher("/background/costs/costsupdate.jsp").forward(request,response);

    }

    protected void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");

        String[] id = ids.split(",");
        int sum=0;
        for (String gradeId:id) {
            int  i = dao.delete(Integer.parseInt(gradeId));
            sum+=i;
        }
        response.getWriter().print(""+sum);
    }


    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("kid");
        int gradeId=-1;
        if(!StringUtil.isEmpty(id)){
            gradeId=Integer.parseInt(id);
        }
        int  i = dao.delete(gradeId);
        response.getWriter().print(""+i);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String gradeId = request.getParameter("gradeId");
        String grade = request.getParameter("grade");
        String vvTax = request.getParameter("vvTax");
        String isnP = request.getParameter("isnP");
        String mainC = request.getParameter("mainC");
        String fuelC = request.getParameter("fuelC");
        String invoiceT = request.getParameter("invoiceT");
        String vManager = request.getParameter("vManager");
        CarDiade carDiade= new CarDiade(grade,Integer.parseInt(vvTax),Integer.parseInt(isnP),Integer.parseInt(mainC),Integer.parseInt(fuelC),Integer.parseInt(invoiceT),Integer.parseInt(vManager));
        int i = dao.addCarDiade(carDiade);

        response.getWriter().print(""+i);

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
        List<CarDiade> CarDiadeList = dao.queryPage(pageBean);


        request.setAttribute("CarDiadeList",CarDiadeList);
        request.setAttribute("pageBean",pageBean);
        request.getRequestDispatcher("/background/costs/costs.jsp").forward(request,response);

    }

}
