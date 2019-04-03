package com.servlet;


import com.dao.MenuDao;
import com.dao.RoleDao;
import com.dao.UserDao;
import com.dao.UserRoleDao;
import com.dao.impl.MenuDaoImpl;
import com.dao.impl.RoleDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.dao.impl.UserRoleDaoImpl;
import com.entity.*;
import com.util.DateUtil;
import com.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@WebServlet(name = "UserServlet",urlPatterns = {"/UserServlet"},initParams = {@WebInitParam(name="pageCount",value="5")})
public class UserServlet extends HttpServlet {

    UserDao dao =  new UserDaoImpl();
    RoleDao roleDao =  new RoleDaoImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        System.out.println(action);
        if("query".equals(action)){
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
            pageBean.setCount(dao.getCount());
            // list数据
            request.setAttribute("pageBean",pageBean);

            List<User1> list = dao.queryPageUser(pageBean);

            request.setAttribute("list",list);
            request.getRequestDispatcher("/background/admin/admin-list.jsp").forward(request,response);
        }
        if ("queryOne".equals(action)){

            String role = request.getParameter("role");
            System.out.println("role="+role);

            String username = request.getParameter("username");
            System.out.println("username="+username);

            User user = dao.queryUserOne(username,role);
            System.out.println("user="+user.toString());
            request.getSession().setAttribute("user",user);
            if (user!=null){
                response.getWriter().print(1);
            }else{
                response.getWriter().print(0);
            }
        }
        if("toRole".equals(action)){
            String uId = request.getParameter("uId");
            User1 user = dao.qeruyOne(uId);
            request.setAttribute("user",user);
            List<Role> roleList = roleDao.qeruyAll();
            request.setAttribute("roleList",roleList);
            request.getRequestDispatcher("/background/admin/admin-edit.jsp").forward(request,response);
        }
        if("add".equals(action)){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            int i = dao.add(new User1(username,password));
            System.out.println(i);
            response.getWriter().print(i);
        }
        if("addRole".equals(action)){
            String uId = request.getParameter("uId");
            String rId = request.getParameter("rId");
            UserRoleDao userRoleDao = new UserRoleDaoImpl();
            int i = userRoleDao.addUserRole(new UserRole(Integer.parseInt(uId),Integer.parseInt(rId)));
            response.getWriter().print(i);
        }
        if("login".equals(action)){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User1 user = dao.queryOneByUsername(username);
            System.out.println(user.getuId());
            MenuDao menuDao = new MenuDaoImpl();
            List<Menu> menus = menuDao.RoleMenu(user.getuId());
            System.out.println(menus.toArray());
            request.getSession().setAttribute("menus",menus);

            if(user.getPassword().equals(password)) {
                response.getWriter().print(1);
                request.getSession().setAttribute("currentDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
                loginInfo(request, response);
            }else{
                response.getWriter().print(0);
            }
        }
        if ("delete".equals(action)){
            String kid = request.getParameter("kid");
            int uId=-1;
            if(!StringUtil.isEmpty(kid)){
                uId=Integer.parseInt(kid);
            }
            int  e = dao.deleteRole(uId);
            int  i = dao.deleteUser(uId);
            response.getWriter().print(""+i);
        }
        if ("deleteAll".equals(action)){
            String ids = request.getParameter("ids");
            String[] id = ids.split(",");
            System.out.println(ids);
            int sum=0;
            for (String uId:id) {
                int  e = dao.deleteRole(Integer.parseInt(uId));
                int  i = dao.deleteUser(Integer.parseInt(uId));
                sum+=i;
            }
            response.getWriter().print(""+sum);
        }

    }
    private void loginInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        session.setAttribute("username",username);

    }
}
