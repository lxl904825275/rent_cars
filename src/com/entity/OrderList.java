package com.entity;

import java.sql.Date;

/**
 * @author: Iman
 * date   : 2019/3/26
 */
public class OrderList {
    private String orderId;
    private String name;
    private String sex;
    private String tel;
    private String grade;
    private String carName;
    private String lease;
    private String orderTime;
    private String overTime;
    private int allCost;
    private int clientId;
    private int gradeId;
    private int netIncome;
    private  int costs;
    private int count;

    public OrderList(String orderId, String name, String tel, String carName, String lease, String orderTime) {
        this.orderId = orderId;
        this.name = name;
        this.tel = tel;
        this.carName = carName;
        this.lease = lease;
        this.orderTime = orderTime;
    }

    public OrderList(String orderId, String name, String tel, String carName, String lease, String orderTime, String overTime, int allCost) {
        this.orderId = orderId;
        this.name = name;
        this.tel = tel;
        this.carName = carName;
        this.lease = lease;
        this.orderTime = orderTime;
        this.overTime = overTime;
        this.allCost = allCost;
    }
    public OrderList(String orderId, String overTime) {
        this.orderId = orderId;
        this.orderTime = orderTime;
    }

    public OrderList(String name, String tel, String carName, String lease, String orderTime, String overTime, int allCost) {
        this.name = name;
        this.tel = tel;
        this.carName = carName;
        this.lease = lease;
        this.orderTime = orderTime;
        this.overTime = overTime;
        this.allCost = allCost;
    }

    public OrderList(int gradeId, String grade, int count, int allCost, int costs ,int netIncome) {

        this.grade = grade;
        this.allCost = allCost;
        this.gradeId = gradeId;
        this.netIncome = netIncome;
        this.costs = costs;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public OrderList(String orderId, String name, int allCost, int costs, int netIncome) {
        this.orderId = orderId;
        this.name = name;
        this.allCost = allCost;

        this.costs = costs;
        this.netIncome = netIncome;
    }

    public int getCosts() {
        return costs;
    }

    public void setCosts(int vvTax,int isnP,int mainC,int fuelC,int invoiceT,int vManager) {

        this.costs = vvTax+isnP+mainC+fuelC+invoiceT+vManager;
    }

    public int getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(int allcost,int costs) {
        this.netIncome = allcost-costs;
    }


    public OrderList(String orderId, String name, String sex, String tel, String carName, String lease, String orderTime, String overTime, int allCost, int clientId, int gradeId) {
        this.orderId = orderId;
        this.name = name;
        this.sex = sex;
        this.tel = tel;

        this.carName = carName;
        this.lease = lease;
        this.orderTime = orderTime;
        this.overTime = overTime;
        this.allCost = allCost;
        this.clientId=clientId;
        this.gradeId = gradeId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public OrderList() {
    }

    public OrderList(String name, String sex, String tel,  String carName, String lease, String orderTime, String overTime, int allCost,int gradeId) {
        this.name = name;
        this.sex = sex;
        this.tel = tel;

        this.carName = carName;
        this.lease = lease;
        this.orderTime = orderTime;
        this.overTime = overTime;
        this.allCost = allCost;
        this.gradeId=gradeId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }



    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getLease() {
        return lease;
    }

    public void setLease(String lease) {
        this.lease = lease;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }

    public int getAllCost() {
        return allCost;
    }

    public void setAllCost(int allCost) {
        this.allCost = allCost;
    }
}
