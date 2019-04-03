package com.servlet;



import com.dao.MenuDao;
import com.dao.impl.MenuDaoImpl;
import com.entity.Menu;
import com.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.cn;


@WebServlet(name = "MenuSerlvet",urlPatterns = {"/MenuSerlvet"})
public class MenuSerlvet extends HttpServlet {

    MenuDao dao =  new MenuDaoImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if("query".equals(action)){
            List<Menu> list = dao.qeruyAll();
            request.setAttribute("list",list);
            request.getRequestDispatcher("/background/admin/admin-cate.jsp").forward(request,response);
        }
        if ("delete".equals(action)){
            String kid = request.getParameter("kid");
            int uId=-1;
            if(!StringUtil.isEmpty(kid)){
                uId=Integer.parseInt(kid);
            }
            int  e = dao.deleteRole(uId);
            int  i = dao.deleteMenu(uId);
            response.getWriter().print(""+i);
        }
        if ("deleteAll".equals(action)){
            String ids = request.getParameter("ids");
            String[] id = ids.split(",");
            System.out.println(ids);
            int sum=0;
            for (String uId:id) {
                int  e = dao.deleteRole(Integer.parseInt(uId));
                int  i = dao.deleteMenu(Integer.parseInt(uId));
                sum+=i;
            }
            response.getWriter().print(""+sum);
        }
        if("queryOne".equals(action)){
            String kid = request.getParameter("kid");
            String  mId = kid;
            Menu menu = dao.queryOne(Integer.parseInt(mId));
            request.setAttribute("menu",menu);
            request.getRequestDispatcher("/background/admin/admin-cateEdit.jsp").forward(request,response);
        }
        if ("update".equals(action)){
            String mId = request.getParameter("mId");
            String menuName = request.getParameter("menuName");
            String pId = request.getParameter("pId");
            String url = request.getParameter("url");
            Menu menu = new Menu(Integer.parseInt(mId),Integer.parseInt(pId),menuName,url);
            int i = dao.updateMenu(menu);
            System.out.println(i);
            response.getWriter().print(""+i);
        }
    }

}
