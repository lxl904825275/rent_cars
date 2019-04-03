<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/30 0030
  Time: 下午 4:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-3">
    <div class="category-sidebar">
        <div class="widget category-list">
            <h4 class="widget-header">车辆款式</h4>
            <ul class="category-list" id="demo">
                <li><a href="<%=request.getContextPath()%>/CarsServlet?action=findC">C级车</a></li>
                <li><a href="<%=request.getContextPath()%>/CarsServlet?action=findD">D级车</a></li>
                <li><a href="<%=request.getContextPath()%>/CarsServlet?action=findSuv">SUV</a></li>
            </ul>
        </div>

        <div class="widget category-list">
            <h4 class="widget-header">车辆名称</h4>
            <ul class="category-list">
                <li><a href="<%=request.getContextPath()%>/CarsServlet?action=findDazhong">大众</a></li>
                <li><a href="<%=request.getContextPath()%>/CarsServlet?action=findBieke">别克</a></li>
                <li><a href="<%=request.getContextPath()%>/CarsServlet?action=findAodi">奥迪</a></li>
                <li><a href="<%=request.getContextPath()%>/CarsServlet?action=findBaoma">宝马</a></li>
                <li><a href="<%=request.getContextPath()%>/CarsServlet?action=findWoerwo">沃尔沃</a></li>
                <li><a href="<%=request.getContextPath()%>/CarsServlet?action=findXiandai">现代</a></li>
            </ul>
        </div>
    </div>
</div>
