<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/26
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/background/infoo.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">演示</a>
        <a>
          <cite>车辆费用支出管理</cite></a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">

    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn layui-btn-danger" onclick="x_admin_show('添加用户','<%=request.getContextPath()%>/background/costs/costsadd.jsp')"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：${pageBean.count} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>类型编号</th>
            <th>类型名称</th>
            <th>车船税支出</th>
            <th>保险费支出</th>
            <th>维修保养费支出</th>
            <th>加油费支出</th>
            <th>发票税费支出</th>
            <th>车型管理费</th>
            <th>总费用</th>

            <th >操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${CarDiadeList}" var="carDiades">

            <tr>
                <td>
                    <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${carDiades.gradeId}'><i class="layui-icon">&#xe605;</i></div>
                </td>
                <td>${carDiades.gradeId}</td>
                <td>${carDiades.grade}</td>
                <td>${carDiades.vvTax}</td>
                <td>${carDiades.isnP}</td>
                <td>${carDiades.mainC}</td>
                <td>${carDiades.fuelC}</td>
                <td>${carDiades.invoiceT}</td>
                <td>${carDiades.vManager}</td>
                <td>${carDiades.costs}</td>
                <td class="td-manage">
                    <a title="查看"  onclick="x_admin_show('编辑','<%=request.getContextPath()%>/CarDiadeServlet?action=queryOne&kid=${carDiades.gradeId}')" href="javascript:;">
                        <i class="layui-icon">&#xe63c;</i>
                    </a>
                    <a title="删除" onclick="member_del(this,'${carDiades.gradeId}')" href="javascript:;">
                        <i class="layui-icon">&#xe640;</i>
                    </a>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="page">
        <div>
            <a class="prev" href="">&lt;&lt;</a>
            <c:forEach var="i" begin="1" end="${pageBean.pages}" step="1">

                <c:if test="${i==pageBean.pageIndex}">
                    <span class="current">${i}</span>
                </c:if>

                <c:if test="${i!=pageBean.pageIndex}">
                    <a class="num" href="<%=request.getContextPath()%>/CarDiadeServlet?action=query&pageIndex=${i}">${i}</a>
                </c:if>
            </c:forEach>
            <a class="next" href="">&gt;&gt;</a>
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
                url:"<%=request.getContextPath()%>/CarDiadeServlet",
                data:"action=delete&kid="+id,
                success:function(msg){
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
    };

    function delAll (argument) {

        var data = tableCheck.getData();
        if (data == "") {
            layer.msg('请至少选择1条数据');
            return;
        }
        layer.confirm('确认要删除这些信息吗？', function (index) {
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/CarDiadeServlet",
                data: "action=deleteAll&ids=" + data,
                success: function (msg) {
                    if (msg > 0) {
                        //捉到所有被选中的，发异步进行删除
                        layer.msg('成功删除' + msg + '条数据', {icon: 1})
                    } else {
                        layer.msg('已删除或不存在!', {icon: 1, time: 1000});
                    }
                    location.reload();
                }
            });

        });
    }



</script>



</body>
</html>
