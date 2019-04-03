<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/20
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>汽车租赁</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <%@include file="/background/infoo.jsp"%>
</head>

<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <label for="orderId" class="layui-form-label">
                <span class="x-red">*</span>订单编号
            </label>
            <div class="layui-input-inline">
                <input value="${orderList.orderId}" type="text" id="orderId" name="orderId" readonly="readonly" required="orderId" lay-verify="orderId"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="name" class="layui-form-label">
                <span class="x-red">*</span>客户姓名
            </label>
            <div class="layui-input-inline">
                <input value="${orderList.name}" type="text" id="name" name="name" required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="tel" class="layui-form-label">
                <span class="x-red">*</span>电话
            </label>
            <div class="layui-input-inline">
                <input  value="${orderList.tel}" type="tel" id="tel" name="tel" required="" lay-verify="tel"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="carName" class="layui-form-label">
                <span class="x-red">*</span>汽车品牌
            </label>
            <div class="layui-input-inline">
                <input  value="${orderList.carName}" type="carName" id="carName" name="carName" required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="lease" class="layui-form-label">
                <span class="x-red">*</span>租赁总时间
            </label>
            <div class="layui-input-inline">
                <input  value="${orderList.lease}" type="carName" id="lease" name="lease" required="" lay-verify="required"
                        autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="orderTime" class="layui-form-label">
                <span class="x-red">*</span>租车时间
            </label>
            <div class="layui-input-inline">
                <input  value="${orderList.orderTime}" type="orderTime" id="orderTime" name="orderTime" required="" lay-verify="required"
                        autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="overTime" class="layui-form-label">
                <span class="x-red">*</span>还车时间
            </label>
            <div class="layui-input-inline">
                <input  value="${orderList.overTime}" type="overTime" id="overTime" name="overTime" required="" lay-verify="required"
                        autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="allCost" class="layui-form-label">
                <span class="x-red">*</span>总花销
            </label>
            <div class="layui-input-inline">
                <input  value="${orderList.allCost}" type="allCost" id="allCost" name="allCost" required="" lay-verify="required"
                        autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label  class="layui-form-label">
            </label>
            <button  class="layui-btn" lay-filter="update" lay-submit="">
                修改
            </button>
        </div>
</form>
</div>
<script>
    layui.use(['form','layer'], function(){
        $ = layui.jquery;
        var form = layui.form
            ,layer = layui.layer;
        //监听提交
        form.on('submit(update)', function(data){
            var orderId=data.field.orderId;
            var name=data.field.name;
            var tel=data.field.tel;
            var carName=data.field.carName;
            var lease=data.field.lease;
            var orderTime=data.field.orderTime;
            var overTime=data.field.overTime;
            var allCost=data.field.allCost;
            console.log(data)
            $.ajax({
                type:"post",
                url:"<%=request.getContextPath()%>/OrderListServlet",
                data:{
                    "action":"update",
                    "orderId":orderId,
                    "name":name,
                    "tel":tel,
                    "carName":carName,
                    "lease":lease,
                    "orderTime":orderTime,
                    "overTime":overTime,
                    "allCost":allCost
                },
                success:function(msg){
                      if(msg=="1"){
                          //发异步，把数据提交给java
                          layer.alert("修改成功", {icon: 6},function () {
                              // 获得frame索引
                              var index = parent.layer.getFrameIndex(window.name);
                              //关闭当前frame
                              parent.layer.close(index);
                              window.parent.location.reload();
                          });
                      }else{
                          layer.msg("信息不存在，修改失败")
                      }
                },
                error:function () {
                    layer.msg("修改异常")
                }
            })
            return false;
        });
    });
</script>
</body>

</html>