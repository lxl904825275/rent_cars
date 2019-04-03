package com.servlet;

import com.alibaba.fastjson.JSONObject;
import com.dao.CarDiadeDao;
import com.dao.CarsDao;
import com.dao.impl.CarDiadeDaoImpl;
import com.dao.impl.CarsDaoImpl;
import com.entity.CarDiade;
import com.entity.Cars;
import com.entity.PageBean;
import com.service.CarsService;
import com.util.JsonUtil;
import com.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @autor : WL;
 * @date : 2019/3/28 0028
 */
@WebServlet(name = "CarsServlet",urlPatterns = "/CarsServlet")
public class CarsServlet extends HttpServlet {
    private CarsService carsService = new CarsService();
    CarsDao dao=new CarsDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String action = request.getParameter("action");
        if(action.equals("queryAll")){
            queryAll(request,response);
        }else if(action.equals("findSuv")){
            findSuv(request,response);
        }else if(action.equals("findC")){
            findC(request,response);
        }else if(action.equals("findD")){
            findD(request,response);
        }else if(action.equals("findBaoma")){
            findBaoma(request,response);
        }else if(action.equals("findAodi")){
            findAodi(request,response);
        }else if(action.equals("findBieke")){
            findBieke(request,response);
        }else if(action.equals("findXiandai")){
            findXiandai(request,response);
        }else if(action.equals("findDazhong")){
            findDazhong(request,response);
        }else if(action.equals("findWoerwo")){
            findWoerwo(request,response);
        }else if("query".equals(action)){
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
        }else if("queryPage".equals(action)) {
            queryPage(request, response);
        }else if("queryOneBack".equals(action)) {
            queryOneBack(request, response);
        }else if("toadd".equals(action)){
            toadd(request, response);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void queryOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String carName = request.getParameter("carName");
        String gradeId = request.getParameter("gradeId");
//        String isImage = request.getParameter("is_image");
//        String imageUrl = request.getParameter("imageUrl");
        String dailyRent = request.getParameter("dailyRent");
        String monthRent = request.getParameter("monthRent");
        String num = request.getParameter("num");
        String context = request.getParameter("context");
        String image = (String)request.getSession().getAttribute("image");
        request.getSession().removeAttribute("image");

        Cars cars=new Cars(carName,Integer.parseInt(gradeId),image!=null?image:"",Integer.parseInt(dailyRent),Integer.parseInt(monthRent),Integer.parseInt(num),context);
        int i = dao.addCars(cars);
        System.out.println(i+"=======");
        response.getWriter().print(""+i);

    }
    private void queryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageIndex = request.getParameter("page");
        PageBean pageBean =  new PageBean();
        if(!StringUtil.isEmpty(pageIndex)){
            pageBean.setPageIndex(Integer.parseInt(pageIndex));
        }
//        pageBean.setPageCount(Integer.parseInt(getInitParameter("pageCount")));
        String pageCount = request.getParameter("limit");
        pageBean.setPageCount(Integer.parseInt(pageCount));
        pageBean.setCount(dao.findAll().size());
        List<Cars> carsList= dao.queryPage(pageBean );

        JSONObject jsonObject = JsonUtil.getJsonObject(carsList, pageBean);
        response.getWriter().print(jsonObject);
    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String carName = request.getParameter("carName");
        String gradeId = request.getParameter("gradeId");
        String dailyRent = request.getParameter("dailyRent");
        String monthRent = request.getParameter("monthRent");
        String num = request.getParameter("num");
        String context = request.getParameter("context");
        Cars cars=new Cars(Integer.parseInt(id),carName,Integer.parseInt(gradeId),Integer.parseInt(dailyRent),Integer.parseInt(monthRent),Integer.parseInt(num),context);
        int i = dao.updateCars(cars);

        response.getWriter().print(""+i);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int i =  dao.delete(Integer.parseInt(id));
        System.out.println(i+"-----------------------");
        response.getWriter().print(i);
    }
    protected void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        String[] idArr = ids.split(",");
        int sum=0;
        for (String id:idArr) {
            int i =  dao.delete(Integer.parseInt(id));
            sum+=i;
        }
        response.getWriter().print(sum);
    }
    protected void queryOneBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        Cars cars = dao.findCarsById(Integer.parseInt(id));
        CarDiadeDao carDiadeDao=new CarDiadeDaoImpl();
        List<CarDiade> carDiades = carDiadeDao.findAll();
        request.setAttribute("cars",cars);
        request.setAttribute("carDiades",carDiades);
        request.getRequestDispatcher("/background/cars/carsUpdate.jsp").forward(request,response);


    }
    private void toadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarDiadeDao carDiadeDao=new CarDiadeDaoImpl();
        List<CarDiade> carDiades = carDiadeDao.findAll();
//        System.out.println(carDiades+"-----");
        request.setAttribute("carDiades",carDiades);
        request.getRequestDispatcher("/background/cars/carsAdd.jsp").forward(request,response);
    }

    protected void findBaoma(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cars> baoma = carsService.findBaoma();
        request.setAttribute("baoma",baoma);
        request.getRequestDispatcher("/baomacar.jsp").forward(request,response);

    }

    protected void findWoerwo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cars> woerwo = carsService.findWoerwo();
        request.setAttribute("woerwo",woerwo);
        request.getRequestDispatcher("/woerwocar.jsp").forward(request,response);

    }
    protected void findDazhong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cars> dazhong = carsService.findDazhong();
        request.setAttribute("dazhong",dazhong);
        request.getRequestDispatcher("/Dazongcar.jsp").forward(request,response);

    }
    protected void findXiandai(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cars> xiandai = carsService.findXiandai();
        request.setAttribute("xiandai",xiandai);
        request.getRequestDispatcher("/xiandaicar.jsp").forward(request,response);

    }
    protected void findBieke(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cars> bieke = carsService.findBieke();
        request.setAttribute("bieke",bieke);
        request.getRequestDispatcher("/biekecar.jsp").forward(request,response);

    }
    protected void findAodi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cars> aodi = carsService.findAodi();
        request.setAttribute("aodi",aodi);
        request.getRequestDispatcher("/aodicar.jsp").forward(request,response);
    }
    protected void findD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cars> carD = carsService.findD();
        request.setAttribute("carD",carD);
        request.getRequestDispatcher("/Dcar.jsp").forward(request,response);
    }
    protected void findSuv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cars> carSUV = carsService.findSuv();
        request.setAttribute("carSUV",carSUV);
        request.getRequestDispatcher("/SUVcar.jsp").forward(request,response);
    }
    protected void findC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cars> carC = carsService.findC();
        request.setAttribute("carC",carC);
        request.getRequestDispatcher("/Ccar.jsp").forward(request,response);
    }
    protected void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cars> query = carsService.query();
        System.out.println(query);
        request.setAttribute("query",query);
        request.getRequestDispatcher("/carAll.jsp").forward(request,response);
    }
}
