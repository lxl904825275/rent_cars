<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/20
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/background/infoo.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>汽车租赁</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
</head>
<body>
<div class="x-body">
    <form action="" method="post" class="layui-form layui-form-pane">
        <div class="layui-form-item">
            <label for="orderId" class="layui-form-label">
                <span class="x-red">*</span>搜索订单ID
            </label>
            <div class="layui-input-inline">
                <input type="tel" id="orderId" name="orderId" required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <button class="layui-btn" lay-submit="" lay-filter="findOne">搜索</button>
        </div>
    </form>
</div>
<script>
    layui.use(['form','layer'], function(){
        $ = layui.jquery;
        var form = layui.form
            ,layer = layui.layer;

        //监听提交
        form.on('submit(findOne)', function(data){
            console.log(data);
            $.ajax({
                type:"post",
                url:"<%=request.getContextPath()%>/OrderListServlet?action=findOne",
                data:"kid="+data.field.orderId,
                success:function(msg){
                    if(msg==1){
                        //发异步，把数据提交给php
                        layer.alert("查询成功", {icon: 6},function () {
                            location.href='<%=request.getContextPath()%>/background/order/detal.jsp'
                        });
                    }else{
                        layer.alert("查询失败")
                        layer.msg(JSON.stringify(data.field));
                    }
                }
            })
            return false;
        });
    });
</script>
</body>
</html>