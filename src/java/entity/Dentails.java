/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

/**
 *
 * @author admin
 */
@Entity

public class Dentails {
    @Id
    int OrderID;
    int productID;
    int price;
    int Amount;

    public Dentails(int OrderID, int productID, int price, int Amount) {
        this.OrderID = OrderID;
        this.productID = productID;
        this.price = price;
        this.Amount = Amount;
    }

    public int getOrderID() {
        return OrderID;
    }

    public int getProductID() {
        return productID;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return Amount;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }
    
    
}
