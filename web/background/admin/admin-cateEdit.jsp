<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="x-admin-sm">
  
  <head>
    <meta charset="UTF-8">
    <title>汽车租赁-欢迎页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
      <%@ page contentType="text/html;charset=UTF-8" language="java" %>
      <%@include file="/background/infoo.jsp"%>
  </head>
  
  <body>
    <div class="x-body">
        <form class="layui-form">
            <div class="layui-form-item">
                <label for="mId" class="layui-form-label">
                    <span class="x-red"></span>
                </label>
                <div class="layui-input-inline">
                    <input type="number" id="mId" name="mId" value="${menu.mId}"
                           autocomplete="off" value="admin" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red"></span>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="menuName" class="layui-form-label">
                    <span class="x-red"></span>
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="menuName" name="menuName" value="${menu.menuName}"
                           autocomplete="off" value="admin" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red"></span>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="pId" class="layui-form-label">
                    <span class="x-red"></span>
                </label>
                <div class="layui-input-inline">
                    <input type="number" id="pId" name="pId" value="${menu.pId}"
                           autocomplete="off" value="admin" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red"></span>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="url" class="layui-form-label">
                    <span class="x-red"></span>
                </label>
                <div class="layui-input-inline">
                    <input type="url" id="url" name="url" value="${menu.url}"
                           autocomplete="off" value="admin" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red"></span>
                </div>
            </div>
          <div class="layui-form-item">
              <button  class="layui-btn" lay-filter="update" lay-submit="">
                  修改菜单
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
                var mId=data.field.mId;
                var menuName=data.field.menuName;
                var pId=data.field.pId;
                var url=data.field.url;
                console.log(data)
                $.ajax({
                    type:"post",
                    url:"<%=request.getContextPath()%>/MenuSerlvet",
                    data:{
                        "action":"update",
                        "mId":mId,
                        "menuName":menuName,
                        "pId":pId,
                        "url":url,
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