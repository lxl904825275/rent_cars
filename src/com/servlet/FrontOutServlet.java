package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @autor : WL;
 * @date : 2019/4/2 0002
 */
@WebServlet(name = "FrontOutServlet",urlPatterns = "/FrontOutServlet")
public class FrontOutServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }
}
