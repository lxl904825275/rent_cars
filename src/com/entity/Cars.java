package com.entity;

/**
 * @autor : WL;
 * @date : 2019/3/26 0026
 */
public class Cars {
    private int carId;
    private String carName;
    private int gradeId;
    private int isImage;
    private String imageUrl;
    private int dailyRent;
    private int monthRent;
    private int num;
    private String context;

    public Cars(String carName, int gradeId, int isImage, String imageUrl, int dailyRent, int monthRent, int num, String context) {
        this.carName = carName;
        this.gradeId = gradeId;
        this.isImage = isImage;
        this.imageUrl = imageUrl;
        this.dailyRent = dailyRent;
        this.monthRent = monthRent;
        this.num = num;
        this.context = context;
    }

    public Cars(int carId, String carName, int gradeId, int isImage, String imageUrl, int dailyRent, int monthRent, int num, String context) {
        this.carId = carId;
        this.carName = carName;
        this.gradeId = gradeId;
        this.isImage = isImage;
        this.imageUrl = imageUrl;
        this.dailyRent = dailyRent;
        this.monthRent = monthRent;
        this.num = num;
        this.context = context;
    }
    public Cars(String carName, int gradeId,String imageUrl, int dailyRent, int monthRent, int num, String context) {
        this.carName = carName;
        this.gradeId = gradeId;
        this.imageUrl = imageUrl;
        this.dailyRent = dailyRent;
        this.monthRent = monthRent;
        this.num = num;
        this.context = context;
    }

    public Cars(int carId, String carName, int gradeId, int dailyRent, int monthRent, int num, String context) {
        this.carId = carId;
        this.carName = carName;
        this.gradeId = gradeId;
        this.dailyRent = dailyRent;
        this.monthRent = monthRent;
        this.num = num;
        this.context = context;
    }

    @Override
    public String toString() {
        return "Cars{" +
                "carId=" + carId +
                ", carName='" + carName + '\'' +
                ", gradeId=" + gradeId +
                ", isImage=" + isImage +
                ", imageUrl='" + imageUrl + '\'' +
                ", dailyRent=" + dailyRent +
                ", monthRent=" + monthRent +
                ", num=" + num +
                ", context='" + context + '\'' +
                '}';
    }

    public int getCarId() {
        return carId;
    }

    public void setCar_Id(int carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCar_Name(String carName) {
        this.carName = carName;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGrade_Id(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getIsImage() {
        return isImage;
    }

    public void setIs_Image(int isImage) {
        this.isImage = isImage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImage_Url(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDailyRent() {
        return dailyRent;
    }

    public void setDaily_Rent(int dailyRent) {
        this.dailyRent = dailyRent;
    }

    public int getMonthRent() {
        return monthRent;
    }

    public void setMonth_Rent(int monthRent) {
        this.monthRent = monthRent;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Cars() {
    }

    
}
