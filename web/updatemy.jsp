<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./foreground/info.jsp"%>
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
<section class="user-profile section">
    <div class="container">
        <div class="row">
            <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                <div class="sidebar">
                    <jsp:include page="foreground/myxinxi.jsp"></jsp:include>
                </div>
            </div>
            <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">
                <!-- Edit Personal Info -->
                <div class="widget personal-info">
                    <h3 class="widget-header user">编辑个人信息</h3>
                    <form action="<%=request.getContextPath()%>/ClientServlet?action=updat" method="post">
                        <div class="form-group">
                            <label for="id">编号</label>
                            <input type="text" class="form-control" id="id" readonly>
                        </div>
                        <div class="form-group">
                            <label for="name">姓名</label>
                            <input type="text" class="form-control" id="name">
                        </div>
                        <div class="form-group">
                            <label for="age">年龄</label>
                            <input type="text" class="form-control" id="age">
                        </div>
                        <div class="form-group">
                            <label for="sex">年龄</label>
                            <input type="text" class="form-control" id="sex">
                        </div>
                        <div class="form-group">
                            <label for="idNumber">身份证</label>
                            <input type="text" class="form-control" id="idNumber">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email">
                        </div>
                        <div class="form-group">
                            <label for="tel">联系方式</label>
                            <input type="text" class="form-control" id="tel">
                        </div>
                        <div class="form-group">
                            <label for="driveNumber">驾驶证编号</label>
                            <input type="email" class="form-control" id="driveNumber">
                        </div>
                        <div class="form-group">
                            <label for="driveType">驾驶证类型</label>
                            <input type="email" class="form-control" id="driveType">
                        </div>
                        <div class="form-group">
                            <label for="address">家庭住址</label>
                            <input type="email" class="form-control" id="address">
                        </div>
                        <!-- Submit button -->
                        <input type="submit" value="确认修改">
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="foreground/commons/link.jsp"></jsp:include>
<jsp:include page="foreground/commons/foot.jsp"></jsp:include>

</body>
</html>
