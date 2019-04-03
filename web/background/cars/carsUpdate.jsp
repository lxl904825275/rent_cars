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

</head>
<body>
    <div class="layui-container" style="padding: 20px">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <input type="hidden" name="id" value="${cars.carId}" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">车名：</label>
                <div class="layui-input-block">
                    <input type="text" name="carName" required value="${cars.carName}"  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">车型：</label>
                <div class="layui-input-inline">
                    <input type="text" name="gradeId" required lay-verify="required" value="${cars.gradeId}"  placeholder="请输入作者" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">车型：</label>
                <div class="layui-input-block">
                    <select name="gradeId" lay-verify="required">
                        <option value=""></option>
                        <c:forEach items="${carDiades}" var="carDiades">
                            <c:if test="${carDiades.gradeId == carDiades.gradeId}">
                                <option selected value="${carDiades.gradeId}">${carDiades.grade}</option>
                            </c:if>
                            <c:if test="${carDiades.gradeId != carDiades.gradeId}">
                                <option value="${carDiades.gradeId}">${carDiades.grade}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">日租价：</label>
                <div class="layui-input-inline">
                    <input type="text" name="dailyRent" value="${cars.dailyRent}"  lay-verify="required" class="layui-input" id="dailyRent">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">月租价：</label>
                <div class="layui-input-inline">
                    <input type="text" name="monthRent" value="${cars.monthRent}"  lay-verify="required" class="layui-input" id="monthRent">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">库存数量：</label>
                <div class="layui-input-inline">
                    <input type="text" name="num" value="${cars.num}"  lay-verify="required" class="" id="num">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">描述信息</label>
                <div class="layui-input-inline">
                    <input type="text" name="context" value="${cars.context}" required="" lay-verify="text"
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
        //Demo
        layui.use(['form','layer'], function(){
            $ = layui.jquery;
            var form = layui.form
                ,layer = layui.layer;

            //监听提交
            form.on('submit(update)', function(data){
                // layer.msg(JSON.stringify(data.field)+"==="+CKEDITOR.instances.content.getData());

                var id = data.field.id;
                var carName = data.field.carName;
                var gradeId = data.field.gradeId;
                var dailyRent = data.field.dailyRent;
                var monthRent = data.field.monthRent;
                var num = data.field.num;
                var context =  data.field.context;
                console.log(data)
                $.ajax({
                    type:"post",
                    url:"<%=request.getContextPath()%>/CarsServlet",
                    data:{
                       action:"update",
                       id:id,
                       carName:carName,
                       gradeId:gradeId,
                       dailyRent:dailyRent,
                       monthRent:monthRent,
                       num:num,
                       context:context
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
