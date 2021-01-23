/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author home
 */
public class Item {
    private Product product;
    private int soluong;
    public Item()
    {
        
    }
    public Item(Product product, int soluong)
    {
        this.product = product;
        this.soluong = soluong;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
