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
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="#"><cite>首页</cite></a>
        <a><cite>订单维护</cite></a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <span class="x-right" style="line-height:40px">共有数据：${pageBean.count} 单</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>订单编号</th>
            <th>客户姓名</th>
            <th>电话</th>
            <th>汽车品牌</th>
            <th>租赁总时间</th>
            <th>租车时间</th>
            <th>还车时间</th>
            <th>总花销</th>
            <th >操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderList}" var="order">
            <tr>
                <td>
                    <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${order.orderId}'><i class="layui-icon">&#xe605;</i></div>
                </td>
                <td>${order.orderId}</td>
                <td>${order.name}</td>
                <td>${order.tel}</td>
                <td>${order.carName}</td>
                <td>${order.lease}</td>
                <td>${order.orderTime}</td>
                <td>${order.overTime}</td>
                <td>${order.allCost}</td>
                <td class="td-manage">
                    <a title="删除" onclick="member_del(this,'${order.orderId}')" href="javascript:;">
                        <i class="layui-icon">&#xe640;</i>
                    </a>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="page">
        <div>
            <c:if test="${pageBean.pageIndex!=1}">
                <a class="prev" href="<%=request.getContextPath()%>/OrderListServlet?action=query&pageIndex=${pageBean.pageIndex-1}">&lt;&lt;</a>
            </c:if>
            <c:forEach var="i" begin="1" end="${pageBean.pages}" step="1">
                <c:if test="${i==pageBean.pageIndex}">
                    <span class="current">${i}</span>
                </c:if>
                <c:if test="${i!=pageBean.pageIndex}">
                    <a class="num" href="<%=request.getContextPath()%>/OrderListServlet?action=query&pageIndex=${i}">${i}</a>
                </c:if>
            </c:forEach>
            <c:if test="${pageBean.pageIndex < pageBean.pages}">
                <a class="next" href="<%=request.getContextPath()%>/OrderListServlet?action=query&pageIndex=${pageBean.pageIndex+1}">&lt;&lt;</a>
            </c:if>
        </div>
    </div>
</div>
<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
    });

    /*用户-删除*/
    function member_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type:"post",
                url:"<%=request.getContextPath()%>/OrderListServlet",
                data:"action=delete&id="+id,
                success:function(msg){
                    alert("dsasd")
                    //发异步删除数据
                    $(obj).parents("tr").remove();
                    if(msg==1){
                        layer.msg('删除成功!',{icon:1,time:1000});
                    }else{
                        layer.msg('已删除或不存在!',{icon:1,time:1000});
                    }
                }
            })
        });
    }

    function delAll (argument) {

        var data = tableCheck.getData();
        if(data==""){
            layer.msg('请至少选择1条数据');
            return;
        }
        layer.confirm('确认要删除这些信息吗？',function(index){
            $.ajax({
                type:"post",
                url:"<%=request.getContextPath()%>/OrderListServlet",
                data:"action=deleteAll&ids="+data,
                success:function(msg){
                    if(msg>0){
                        //捉到所有被选中的，发异步进行删除
                        layer.msg('成功删除'+msg+'条数据', {icon: 1})
                    }else{
                        layer.msg('已删除或不存在!',{icon:1,time:1000});
                    }
                    $(".layui-form-checked").not('.header').parents('tr').remove();
                }
            });

        });
    }
</script>
</body>

</html>