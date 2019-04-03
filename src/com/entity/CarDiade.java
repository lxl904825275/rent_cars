package com.entity;

/**
 * @author: Iman
 * date   : 2019/3/27
 * 车船税支出管理、 保险费支出管理、维修保养费支出管理、加油费支出管理、发票税费支出管理,
 * 车型管理、租赁费用标准设置Standard setting of rental fee
 */
public class CarDiade {
    private  int gradeId;
    private  String grade;
    private  int vvTax;
    private  int isnP;
    private  int mainC;
    private  int fuelC;
    private  int invoiceT;
    private  int vManager;
    private  int costs;


    public CarDiade(int gradeId, String grade, int vvTax, int isnP, int mainC, int fuelC, int invoiceT, int vManager) {
        this.gradeId = gradeId;
        this.grade = grade;
        this.vvTax = vvTax;
        this.isnP = isnP;
        this.mainC = mainC;
        this.fuelC = fuelC;
        this.invoiceT = invoiceT;
        this.vManager = vManager;
    }

    public CarDiade(int gradeId, String grade, int vvTax, int isnP, int mainC, int fuelC, int invoiceT, int vManager, int costs) {
        this.gradeId = gradeId;
        this.grade = grade;
        this.vvTax = vvTax;
        this.isnP = isnP;
        this.mainC = mainC;
        this.fuelC = fuelC;
        this.invoiceT = invoiceT;
        this.vManager = vManager;
        this.costs = costs;
    }

    public CarDiade(String grade, int vvTax, int isnP, int mainC, int fuelC, int invoiceT, int costs) {
        this.grade = grade;
        this.vvTax = vvTax;
        this.isnP = isnP;
        this.mainC = mainC;
        this.fuelC = fuelC;
        this.invoiceT = invoiceT;
        this.costs = costs;
    }

    public int getVvTax() {
        return vvTax;
    }

    public void setVvTax(int vvTax) {
        this.vvTax = vvTax;
    }

    public int getIsnP() {
        return isnP;
    }

    public void setIsnP(int isnP) {
        this.isnP = isnP;
    }

    public int getMainC() {
        return mainC;
    }

    public void setMainC(int mainC) {
        this.mainC = mainC;
    }

    public int getFuelC() {
        return fuelC;
    }

    public void setFuelC(int fuelC) {
        this.fuelC = fuelC;
    }

    public int getInvoiceT() {
        return invoiceT;
    }

    public void setInvoiceT(int invoiceT) {
        this.invoiceT = invoiceT;
    }

    public int getvManager() {
        return vManager;
    }

    public void setvManager(int vManager) {
        this.vManager = vManager;
    }

    public CarDiade() {
    }

    public CarDiade(int gradeId, String grade, int costs) {
        this.gradeId = gradeId;
        this.grade = grade;
        this.costs = costs;
    }

    public CarDiade(String grade, int costs) {
        this.grade = grade;
        this.costs = costs;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getCosts() {
        return costs;
    }

    public void setCosts(int vvTax,int isnP,int mainC,int fuelC,int invoiceT,int vManager) {

        this.costs = vvTax+isnP+mainC+fuelC+invoiceT+vManager;
    }

    @Override
    public String toString() {
        return "CarDiade{" +
                "gradeId=" + gradeId +
                ", grade='" + grade + '\'' +
                ", vvTax=" + vvTax +
                ", isnP=" + isnP +
                ", mainC=" + mainC +
                ", fuelC=" + fuelC +
                ", invoiceT=" + invoiceT +
                ", vManager=" + vManager +
                ", costs=" + costs +
                '}';
    }
}
