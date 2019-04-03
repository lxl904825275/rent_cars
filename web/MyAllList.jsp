<%@include file="./foreground/info.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>

    <!-- SITE TITTLE -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Calssimax</title>
</head>

<body class="body-wrapper">
<jsp:include page="foreground/commons/header.jsp"></jsp:include>
<section class="dashboard section">
    <!-- Container Start -->
    <div class="container">
        <!-- Row Start -->
        <div class="row">
            <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                <div class="sidebar">
                    <!-- User Widget -->
                    <jsp:include page="foreground/myxinxi.jsp"></jsp:include>
                </div>
            </div>
            <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">
<div class="widget dashboard-container my-adslist">
    <h3 class="widget-header">我的历史订单</h3>
    <table class="table table-responsive product-dashboard-table">
        <thead>
        <tr>
            <th>图片</th>
            <th>详细信息</th>
            <th class="text-center">费用</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allOrder}" var="allOrder">
            <tr>
                <td class="product-thumb">
                    <img width="80px" height="auto" src="<%=request.getContextPath()%>/carImg/1.jpg"
                         alt="image description"></td>
                <td class="product-details">
                    <h3 class="title">${allOrder.carName}</h3>
                    <span class="add-id"><strong>订单编号:</strong>${allOrder.orderId}</span>
                    <span><strong>下订单时间: </strong><time>${allOrder.orderTime}</time> </span>
                    <span><strong>结束时间: </strong><time>${allOrder.overTime}</time> </span>
                    <span><strong>使用时间: </strong><time>${allOrder.lease}</time> </span>
                    <span class="location"><strong>电话</strong>${allOrder.tel}</span>
                </td>
                <td class="product-category"><span class="categories">${allOrder.allCost}(元)</span></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
            </div>
        </div>
        <!-- Row End -->
    </div>
    <!-- Container End -->
</section>
<jsp:include page="foreground/commons/link.jsp"></jsp:include>
<jsp:include page="foreground/commons/foot.jsp"></jsp:include>

</body>

</html>