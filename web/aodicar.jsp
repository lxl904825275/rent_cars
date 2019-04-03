<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/29 0029
  Time: 上午 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@include file="./foreground/info.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>

    <!-- SITE TITTLE -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>carAll</title>
</head>
<body class="body-wrapper">
<jsp:include page="foreground/commons/header.jsp"></jsp:include>
<section class="page-search">

</section>
<section class="section-sm">
    <div class="container">
        <div class="row">
            <jsp:include page="foreground/carAllLigth.jsp"></jsp:include>
            <div class="col-md-9">
                <div class="category-search-filter">
                    <div class="row">
                        <div class="col-md-6">
                            <strong>车辆信息</strong>
                        </div>
                    </div>
                </div>
                <div class="product-grid-list">
                    <div class="row mt-30">
                        <c:forEach var="query" items="${aodi}" varStatus="i">
                            <div class="col-sm-12 col-lg-4 col-md-6">
                                <!-- product card -->
                                <div class="product-item bg-light">
                                    <div class="card">
                                        <div class="thumb-content">
                                            <!-- <div class="price">$200</div> -->
                                            <a href="<%=request.getContextPath()%>${query.carId}">
                                                <img class="card-img-top img-fluid" src="${query.imageUrl}" alt="Card image cap">
                                            </a>
                                        </div>
                                        <div class="card-body">
                                            <h4 class="card-title"><a href="">${query.carName}</a></h4>
                                            <ul class="list-inline product-meta">
                                                <li class="list-inline-item">
                                                    <a>日租：${query.dailyRent}</a>
                                                </li>
                                                <li class="list-inline-item">
                                                    <a>月租：${query.monthRent}</a>
                                                </li>
                                            </ul>
                                            <p class="card-text">宽敞，舒适</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="foreground/commons/link.jsp"></jsp:include>
<jsp:include page="foreground/commons/foot.jsp"></jsp:include>

</body>
</html>
