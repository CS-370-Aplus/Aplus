package com.example.aplus;

public class Product {
    private String productName;
    private String productGender;
    private double productPrice;
    private String productSize;
    private int productZipCode;
    private String productDescription;


    public void setProductName(String name){
        productName = name;
    }
    public String getProductName(){
        return productName;
    }
    public void setProductGender(String gender){
        productGender = gender;
    }
    public String getProductGender(){
        return productGender;
    }
    public void setProductPrice(double price){
        productPrice = price;
    }
    public double getProductPrice(){
        return productPrice;
    }
    public void setProductSize(String size){
        productSize = size;
    }
    public String getProductSize(){
        return productSize;
    }
    public void setProductZipCode(int zipcode){
        productZipCode = zipcode;
    }
    public int getProductZipCode(){
        return productZipCode;
    }
    public void setProductDescription(String description){
        productDescription = description;
    }
    public String getProductDescription() {
        return productDescription;
    }

    public void addProduct(String Name, String gender, String size, double price, int zipcode, String detail){
        this.setProductName(Name);
        this.setProductGender(gender);
        this.setProductSize(size);
        this.setProductPrice(price);
        this.setProductZipCode(zipcode);
        this.setProductDescription(detail);
    }








}
