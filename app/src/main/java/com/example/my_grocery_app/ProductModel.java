package com.example.my_grocery_app;

public class ProductModel {
    String proImg, proId, proName, proDesc, proPrice;

    public ProductModel(){

    }

    public ProductModel(String proImg, String proId, String proName, String proDesc, String proPrice) {
        this.proImg = proImg;
        this.proId = proId;
        this.proName = proName;
        this.proDesc = proDesc;
        this.proPrice = proPrice;
    }

    public String getProImg() {
        return proImg;
    }

    public void setProId(String proImg) {
        this.proImg = proId;
    }

    public String getProId() {
        return proId;
    }

    public void setProImg(String proImg) {
        this.proImg = proImg;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProPrice() {
        return proPrice;
    }

    public void setProPrice(String proPrice) {
        this.proPrice = proPrice;
    }
}
