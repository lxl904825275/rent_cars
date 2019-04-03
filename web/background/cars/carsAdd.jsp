<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Iman
  Date: 2019/3/22
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/background/infoo.jsp"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
</head>
<body>
<div class="layui-container" style="padding: 20px">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">车名：</label>
            <div class="layui-input-block">
                <input type="text" name="carName" required  lay-verify="required" placeholder="请输入车名" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">车辆类型：</label>
            <div class="layui-input-block">
                <select name="gradeId" lay-verify="required">
                    <option value=""></option>
                    <c:forEach items="${carDiades}" var="carDiades">
                        <option value="${carDiades.gradeId}">${carDiades.grade}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">日租价：</label>
            <div class="layui-input-inline">
                <input type="number" name="dailyRent" lay-verify="required" class="layui-input" id="dailyRent">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">月租价：</label>
            <div class="layui-input-inline">
                <input type="number" name="monthRent" lay-verify="required" class="layui-input" id="monthRent">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">库存数量：</label>
            <div class="layui-input-inline">
                <input type="number" name="num" lay-verify="required" class="layui-input" id="num">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">描述内容：</label>
            <div class="layui-input-block">
                <textarea id="content"  name="content"  cols="20" rows="2" class="ckeditor"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="submitSave">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
    <script>
        //Demo
        layui.use(['laydate','form'], function(){
            var form = layui.form;
            var laydate = layui.laydate;
            //监听提交
            form.on('submit(submitSave)', function(data){
                //layer.msg(JSON.stringify(data.field)+"==="+CKEDITOR.instances.content.getData());


                var carName = data.field.carName;
                var gradeId = data.field.gradeId;
                var dailyRent = data.field.dailyRent;
                var monthRent = data.field.monthRent;
                var num = data.field.num;
                var content =  CKEDITOR.instances.content.getData();
                console.log(data.field.monthRent);

                $.ajax({
                    type:"post",
                    url:"<%=request.getContextPath()%>/CarsServlet",
                    data:{
                        "action":"add",
                        "carName":carName,
                        "gradeId":gradeId,
                        "dailyRent":dailyRent,
                        "monthRent":monthRent,
                        "num":num,
                        "context":content
                    },
                    success:function(msgData){
                        layer.msg("添加成功",{icon:1,time:2000});
                        setTimeout("location.reload()",3000)

                    }
                })
                return false;
            });
            laydate.render({
                elem: '#test1', //指定元素
                type:'datetime'
            });
        });
    </script>
</body>
</html>
