<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>汽车租赁-欢迎页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
      <%@include file="/background/infoo.jsp"%>
  </head>

  <body>
  <div class="x-body">
      <form action="" method="post" class="layui-form layui-form-pane">
          <div class="layui-form-item">
              <label  for="rId">
              </label>
              <div class="layui-input-inline">
                  <input type="hidden" id="rId" name="rId" value="${role.r_id}"
                         autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="rolename" class="layui-form-label">
                  <span class="x-red">*</span>角色名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="rolename" name="rolename" value="${role.role_name}" readonly
                         autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label class="layui-form-label"><span class="x-red">*</span>菜单</label>
              <div class="layui-input-block">
                  <ul>


                      <c:forEach items="${menus}" var="root">

                          <c:if test="${root.pId==-1}">
                              <li>
                                  <input type="checkbox" name="menu" lay-skin="primary" value="${root.mId}" title="${root.menuName}" checked="" >
                                  <ul>
                                      <c:forEach items="${menus}" var="menu">
                                          <c:if test="${menu.pId ==root.mId}">
                                              <li>
                                                  <input type="checkbox" name="menu" lay-skin="primary" value="${menu.mId}" title="${menu.menuName}" >
                                              </li>
                                          </c:if>
                                      </c:forEach>
                                  </ul>

                              </li>
                          </c:if>

                      </c:forEach>
                  </ul>
              </div>
          </div>
          <div class="layui-form-item">
              <button class="layui-btn" lay-submit="" lay-filter="add">授权</button>
          </div>
      </form>
  </div>
  <script>
      layui.use(['form','layer'], function(){
          $ = layui.jquery;
          var form = layui.form
              ,layer = layui.layer;

          //自定义验证规则
          form.verify({
              nikename: function(value){
                  if(value.length < 5){
                      return '昵称至少得5个字符啊';
                  }
              }
              ,pass: [/(.+){6,12}$/, '密码必须6到12位']
              ,repass: function(value){
                  if($('#L_pass').val()!=$('#L_repass').val()){
                      return '两次密码不一致';
                  }
              }
          });

          //监听提交
          form.on('submit(add)', function(data){
              console.log(data);

              //获取checkbox[name='menu']的值
              var arr = new Array();
              $("input:checkbox[name='menu']:checked").each(function(i){
                  arr[i] = $(this).val();
              });
              data.field.menu = arr.join(",");//将数组合并成字符串
              alert(data.field.menu)
              $.ajax({
                  type:"post",
                  url:"<%=request.getContextPath()%>/RoleSerlvet",
                  data:"action=addRolemenu&rId="+data.field.rId+"&mids="+data.field.menu,
                  success:function(msg){
                      //发异步，把数据提交给php
                      layer.alert("增加成功", {icon: 6},function () {
                          // 获得frame索引
                          var index = parent.layer.getFrameIndex(window.name);
                          //关闭当前frame
                          parent.layer.close(index);
                          window.parent.location.reload();
                      });
                  }
              })

              return false;
          });


      });
  </script>

  </body>

</html>