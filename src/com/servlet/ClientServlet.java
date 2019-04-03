package com.servlet;

import com.alibaba.fastjson.JSONObject;
import com.dao.ClientDao;
import com.dao.impl.ClientDaoImpl;
import com.entity.Client;
import com.entity.PageBean;
import com.service.ClientService;
import com.util.JsonUtil;
import com.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClientServlet",urlPatterns = "/ClientServlet",initParams ={@WebInitParam(name="pageCount",value="10")})
public class ClientServlet extends HttpServlet {
    private ClientService service = new ClientService();
    private ClientDao dao = new ClientDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String action = request.getParameter("action");
        if("query".equals(action)){
            query(request, response);
        }else if("queryOne".equals(action)){
            queryOne(request, response);
        }else if("toadd".equals(action)){
            toadd(request, response);
        }else if("add".equals(action)){
            add(request, response);
        }else if("queryPage".equals(action)){
            queryPage(request, response);
        }else if("delete".equals(action)){
            delete(request, response);
        }else if("deleteAll".equals(action)){
            deleteAll(request, response);
        }else if("queryOneBack".equals(action)){
            queryOneBack(request, response);
        }else if("update".equals(action)){
            update(request, response);
        }else if("updat".equals(action)){
            updat(request,response);
        }else if("addd".equals(action)){
            addd(request,response);
        }else if("findAll".equals(action)){
            findAll(request,response);
        }
    }
    protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> allClient = service.findAllClient();
        request.setAttribute("allClient",allClient);

    }
    protected void addd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String idNumber = request.getParameter("idNumber");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String driveNumber = request.getParameter("driveNumber");
        String driveType = request.getParameter("driveType");
        String address = request.getParameter("address");
        // String integral = request.getParameter("integral");
        Client client = new Client(name, sex,Integer.parseInt(age), idNumber, email,
                tel, driveNumber, driveType, address);
        if(client==null ){

        }
        System.out.println("client======"+client);
        HttpSession session = request.getSession();
        session.setAttribute("client",client);

        int i = service.addClient(client);
        System.out.println("i====="+i);
        response.getWriter().print(i);
        request.getRequestDispatcher("/my.jsp").forward(request,response);

    }
    protected void updat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientId = request.getParameter("clientId");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String idNumber = request.getParameter("idNumber");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String driveNumber = request.getParameter("driveNumber");
        String driveType = request.getParameter("driveType");
        String address = request.getParameter("address");
        Client client = new Client(Integer.parseInt(clientId),name, sex, Integer.parseInt(age), idNumber, email,
                tel, driveNumber, driveType, address);
        int i = service.updateClient(client);
        request.setAttribute("client",client);
        request.getSession().setAttribute("client",client);
        request.getRequestDispatcher("/my.jsp").forward(request,response);
        //response.getWriter().print(i);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    protected void queryOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    protected void toadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String idNumber = request.getParameter("idNumber");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String driveNumber = request.getParameter("driveNumber");
        String driveType = request.getParameter("driveType");
        String address = request.getParameter("address");
        String integral = request.getParameter("integral");
        Client client =  new Client(name,sex,Integer.parseInt(age),idNumber,email,
                tel,driveNumber,driveType,address,Integer.parseInt(integral));
        int i = dao.addClient(client);
        System.out.println("1");
        response.getWriter().print(i);
    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientId = request.getParameter("clientId");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String idNumber = request.getParameter("idNumber");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String driveNumber = request.getParameter("driveNumber");
        String driveType = request.getParameter("driveType");
        String address = request.getParameter("address");
        String integral = request.getParameter("integral");
        Client client =  new Client(Integer.parseInt(clientId),name,sex,Integer.parseInt(age),idNumber,email,
                tel,driveNumber,driveType,address,Integer.parseInt(integral));
        int i = dao.updateClient(client);
        response.getWriter().print(i);
    }
    protected void queryOneBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientId = request.getParameter("clientId");
        int id = Integer.parseInt(clientId);

        ClientDao  dao= new ClientDaoImpl();
        Client client = dao.queryOne(id);

        request.setAttribute("client",client);

        request.getRequestDispatcher("/background/client/ClientUpdate.jsp").forward(request,response);

    }
    protected void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        String[] idArr = ids.split(",");
        int sum=0;
        for (String clientId:idArr) {
            int i =  dao.deleteClient(Integer.parseInt(clientId));
            sum+=i;
        }
        response.getWriter().print(sum);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientId = request.getParameter("clientId");
        int i =  dao.deleteClient(Integer.parseInt(clientId));
        response.getWriter().print(i);
    }
    protected void queryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageIndex = request.getParameter("page");
        PageBean pageBean =  new PageBean();
        if(!StringUtil.isEmpty(pageIndex)){
            pageBean.setPageIndex(Integer.parseInt(pageIndex));
        }
        String pageCount = request.getParameter("limit");
        pageBean.setPageCount(Integer.parseInt(pageCount));

        pageBean.setCount(dao.findAll().size());
        List<Client> clientList= dao.queryPageList(pageBean );
        JSONObject jsonObject = JsonUtil.getJsonObject(clientList, pageBean);
        response.getWriter().print(jsonObject);
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
        List<Client> clientList = dao.queryPageList(pageBean);

        request.setAttribute("clientList",clientList);
        request.setAttribute("pageBean",pageBean);
        request.getRequestDispatcher("/background/client/ClientList.jsp").forward(request,response);
    }
}
