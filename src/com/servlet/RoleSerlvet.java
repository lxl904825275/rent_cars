package com.servlet;



import com.dao.MenuDao;
import com.dao.RoleDao;
import com.dao.RoleMenuDao;
import com.dao.impl.MenuDaoImpl;
import com.dao.impl.RoleDaoImpl;
import com.dao.impl.RoleMenuDaoImpl;
import com.entity.Menu;
import com.entity.Role;
import com.entity.RoleMenu;
import com.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "RoleSerlvet",urlPatterns = {"/RoleSerlvet"})
public class RoleSerlvet extends HttpServlet {

    RoleDao dao =  new RoleDaoImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if("query".equals(action)){
            List<Role> list = dao.qeruyAll();
            request.setAttribute("list",list);
            request.getRequestDispatcher("/background/admin/admin-role.jsp").forward(request,response);
        }
        if("add".equals(action)){
            String rolename = request.getParameter("rolename");
            int i = dao.add(new Role(rolename));
            System.out.println("adsa");
            response.getWriter().print(i);
        }
        if("addMenu".equals(action)){
            String rId = request.getParameter("rId");
            Role role = dao.queryOne(rId);
            MenuDao menuDao = new MenuDaoImpl();
            List<Menu> menus = menuDao.qeruyAll();
            request.setAttribute("menus",menus);
            request.setAttribute("role",role);
            request.getRequestDispatcher("/background/admin/role-menu.jsp").forward(request,response);
        }
        if("addRolemenu".equals(action)){
            String rId = request.getParameter("rId");
            RoleMenuDao roleMenuDao = new RoleMenuDaoImpl();
            List<RoleMenu> midList = roleMenuDao.queryByRoleId(rId);
            String mids = request.getParameter("mids");
            String[] ids = mids.split(",");
            for (String mid:ids) {
                if(!midList.contains(mid)){
                    int i = roleMenuDao.addAll(new RoleMenu(Integer.parseInt(rId), Integer.parseInt(mid)));
                }
            }
        }
        if ("delete".equals(action)){
            String kid = request.getParameter("kid");
            int uId=-1;
            if(!StringUtil.isEmpty(kid)){
                uId=Integer.parseInt(kid);
            }
            int  i = dao.deleteRole(uId);
            response.getWriter().print(""+i);
        }
        if ("deleteAll".equals(action)){
            String ids = request.getParameter("ids");
            String[] id = ids.split(",");
            System.out.println(ids);
            int sum=0;
            for (String uId:id) {
                int  i = dao.deleteRole(Integer.parseInt(uId));
                sum+=i;
            }
            response.getWriter().print(""+sum);
        }

    }

}
