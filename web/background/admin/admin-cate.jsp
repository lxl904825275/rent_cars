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
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
      </xblock>
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
              <th>ID</th>
              <th>菜单名</th>
              <th>父节点</th>
              <th>路径</th>
              <th>操作</th>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="menu" >
          <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${menu.mId}'><i class="layui-icon">&#xe605;</i></div>
            </td>
              <td>${menu.mId}</td>
              <td>${menu.menuName}</td>
              <td>${menu.pId}</td>
              <td>${menu.url}</td>
            <td class="td-manage">
              <a title="编辑"  onclick="x_admin_show('编辑','<%=request.getContextPath()%>/MenuSerlvet?action=queryOne&kid=${menu.mId}')" href="javascript:;">
                <i class="layui-icon">&#xe642;</i>
              </a>
              <a title="删除" onclick="member_del(this,'${menu.mId}')" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
              </a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
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

       /*用户-停用*/
      function member_stop(obj,id){
          layer.confirm('确认要停用吗？',function(index){

              if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

              }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
              }
              
          });
      }

      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              $.ajax({
                  type:"post",
                  url:"<%=request.getContextPath()%>/MenuSerlvet",
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
                  url:"<%=request.getContextPath()%>/MenuSerlvet",
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