<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>汽车租赁-后台登录</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <%@include file="/background/infoo.jsp"%>
</head>
<body class="login-bg">
    
    <div class="login layui-anim layui-anim-up">
        <div class="message">汽车租赁-管理登录</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" class="layui-form" >
            <input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
    </div>

    <script>
        $(function  () {
            layui.use('form', function(){
              var form = layui.form;
              //监听提交
              form.on('submit(login)', function(data){
                  $.ajax({
                      type:"POST",
                      url:"<%=request.getContextPath()%>/UserServlet",
                      data:"action=login&username="+data.field.username+"&password="+data.field.password,
                      success:function(msg){
                          var obj = eval("("+msg+")");
                          if(msg==1){
                              //发异步，把数据提交给php
                              layer.alert("登录成功", {icon: 6},function () {
                                  location.href='<%=request.getContextPath()%>/background/commons/backgroundIndex.jsp'

                              });
                              if(${username!=null}){
                                  // 获得frame索引
                                  var index = parent.layer.getFrameIndex(window.name);
                                  //关闭当前frame
                                  parent.layer.close(index);
                                  window.parent.location.reload();
                              }
                          }else{
                              layer.msg(JSON.stringify(data.field));
                          }
                      }
                  });
                return false;
              });
            });
        })

        
    </script>

    
    <!-- 底部结束 -->

</body>
</html>