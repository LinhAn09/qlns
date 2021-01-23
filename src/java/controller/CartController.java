/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Dentails;
import entity.Item;
import entity.Orders;
import entity.Product;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Transactional
@Controller

public class CartController {
     @Autowired
    SessionFactory factory;
    HashMap<Integer, Item> cart;
    
    @RequestMapping("/cart/index")
    public String cartIndex(ModelMap model, HttpSession httpSession){
        httpSession.setAttribute("viewCart", getListItems());
        httpSession.setAttribute("tongtien", getTongTien());
        return "cart/index";
    }
    
    @RequestMapping("addToCart")
    public String addToCart(ModelMap model, HttpSession httpSession,
            @RequestParam("productID") int productID){
        Session session = factory.getCurrentSession();
        
        //Lay mon hang khach can mua
        Product productBuy = (Product) session.get(Product.class, productID);
        cart = (HashMap<Integer, Item>) httpSession.getAttribute("cart");
        //Kiem tra so luong con lai
        if(productBuy.getAmount() > 0){
            //Nhieu hon 0
            
            //Kiểm tra SP đã có trong giỏ hàng chưa
            if(cart.containsKey(productID)){
                //Đã tồn tại trong giỏ hàng
                Item item = cart.get(productID);
                int count = item.getSoluong();
                if(item.getProduct().getAmount() > count){
                    //Số lượng hàng còn lại lớn hơn số lượng mua
                    count = count + 1;
                    item.setSoluong(count);
                }
            }else{
                //Chưa tồn tại
                Item item = new Item(productBuy, 1);
                cart.put(productID, item);
            }
        }else{
            //Het hang
            httpSession.setAttribute("messageCart", "Sản phẩm đã hết hàng");
            return "cart/index";
        }
        return "redirect:/cart/index.htm";
    }
    
    
    
    
    
    @RequestMapping("/cart/delete")
    public String delete(ModelMap model, @RequestParam("productID") int productID) {
             cart.remove(productID);
        return "redirect:/cart/index.htm";
    }
    
    @RequestMapping("/cart/deleteAll")
    public String deleteAll(ModelMap model) {
            removeAll();
        return "redirect:/cart/index.htm";
    }
    
    @RequestMapping("/cart/tang")
    public String tangCart(ModelMap model, @RequestParam("productID") int productID) {
             tang(productID);
        return "redirect:/cart/index.htm";
    }
    
    @RequestMapping("/cart/giam")
    public String giamCart(ModelMap model, @RequestParam("productID") int productID) {
             giam(productID);
        return "redirect:/cart/index.htm";
    }
    
    
    @RequestMapping("/cart/thanhtoan")
    public String thanhtoanCart(ModelMap model, HttpSession httpSession){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            String ngayDH = dateFormat.format(cal.getTime());
            Session session = factory.openSession();
            Transaction t =  session.beginTransaction();
            Orders order = new Orders();
            order.setCustomID(1);
            order.setoDate(ngayDH);
            try {
                //Tạo đơn hàng mới
                session.save(order);
                t.commit();
                //Lấy mã đơn hàng mới nhất vừa mới insert
                Session session2 = factory.getCurrentSession();
                //select sap xep tu cao den thap
                Query query = session2.createQuery("FROM Orders Order by orderID DESC");
                //chi lay 1 gia tri dau tien
                query.setMaxResults(1);
                Orders lastOrder = (Orders) query.uniqueResult();
                int orderID = lastOrder.getOrderID();
                ArrayList<Item> items = getListItems();
                if(items.size() > 0){
                    for(Item item : items){
                        Session ss = factory.openSession();
                        Transaction tx =  session.beginTransaction();
                        Dentails chiTietDonHang = new Dentails(orderID, item.getProduct().getProductID(), item.getProduct().getPrice(), item.getSoluong());
                        
                        ss.save(chiTietDonHang);
                        tx.commit();
                        ss.close();
                            
                    }
                    cart.clear();
                    httpSession.setAttribute("orderID", orderID);
                    httpSession.setAttribute("messageCheckout", "Bạn đã đặt hàng thành công với mã ĐH: "+orderID);
                }else{
                    httpSession.setAttribute("messageCheckout", "Không có món hàng nào trong giỏ");
                }
        } catch (Exception e) {
                e.printStackTrace();
            httpSession.setAttribute("messageCheckout", e);
        } finally{
                session.close();
            }
            
            
        return "redirect:/cart/checkout.htm";
    }
    
    @RequestMapping("/cart/checkout")
    public String checkout(ModelMap model){
        
        return "cart/checkout";
    }
    
    //Ham xoa het sp
    public void removeAll(){
        cart.clear();
    }
    
    //Ham tinh tong tien cac sp trong gio hang
    public int getTongTien(){
        int tong = 0;
        //duyet qua cac sp trong gio hang
        for(Item i: cart.values()){
            tong = tong + i.getSoluong()*i.getProduct().getPrice();
        }
        return tong;
    }
    
    //Ham tang so luong sp
    
    public void tang(int productID){
        // Lay ra Item dua vao masp
        Item item = cart.get(productID);
        //Tang sl
        item.setSoluong(item.getSoluong()+1);
    }
    
    //Ham giam so luong sp
    public void giam(int productID){
        // Lay ra Item dua vao masp
        Item item = cart.get(productID);
        //Giam sl
        item.setSoluong(item.getSoluong()-1);
        if(item.getSoluong()==0){
            cart.remove(productID);
         //   removeSanPham(productID);
        }
    }
    public ArrayList<Item> getListItems() {
        
        ArrayList<Item> listItems = new ArrayList<>();
        // dùng vòng lặp duyệt qua giỏ hàng cart lấy ra từng món hàng item.
        // cart.values() : tập hợp các item trong giỏ hàng
        for (Item i : cart.values()) {
            listItems.add(i);  // add vào danh sách
        }
        return listItems;
    }
}
