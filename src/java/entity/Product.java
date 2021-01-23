/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "Products")

public class Product {
    @Column(name = "productID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int productID;
    private String name;
    private int price;
    private int amount;
    private String proImg;
    private int categoryID;

    public Product() {
    }

    public Product(String name, int price, int amount, String proImg, int categoryID) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.proImg = proImg;
        this.categoryID = categoryID;
    }

    public Product(int productID, String name, int price, int amount, String proImg, int categoryID) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.proImg = proImg;
        this.categoryID = categoryID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProImg() {
        return proImg;
    }

    public void setProImg(String proImg) {
        this.proImg = proImg;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    

    
    
}
