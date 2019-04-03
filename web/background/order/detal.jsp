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
        </div>
    </form>
</div>

</html>
