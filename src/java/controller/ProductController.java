/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Product;
import entity.Categorys;
import entity.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Transactional
@Controller
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    SessionFactory factory;
    HashMap<Integer, Item> cart;
    @RequestMapping("index")
    public String index(ModelMap model, @RequestParam(value = "page", defaultValue = "1") String getpage) {
     
        Session session = factory.getCurrentSession();
        String hql_sum = "Select count(ProductID)FROM Product ";
        Query query_sum = session.createQuery(hql_sum);
        long tong = (long) query_sum.uniqueResult();

        long pages = Integer.parseInt(getpage), maxResult = 8;
        long indexrow = pages * maxResult - maxResult;
        long totalpage = (long) Math.ceil((double) tong / maxResult);

        String hql = "FROM Product"; // lấy hết các tài khoản ra
        Query query = session.createQuery(hql);
        query.setFirstResult((int) indexrow);
        query.setMaxResults((int) maxResult);
        List<Product> list = query.list();
        model.addAttribute("products", list); // lấy danh sách tài khoản list đưa vào thuộc tính users
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("pages", pages);
        return "product/index";
    }

    //1. trang insert hiện thị lên đầu tiên
    @RequestMapping(value = "insert", method = RequestMethod.GET)
    public String insert(ModelMap model) {
        model.addAttribute("product", new Product());
           Session session = factory.getCurrentSession();
           String hql_sum = "FROM Categorys";
        Query query_sum = session.createQuery(hql_sum);
        List<Categorys> cate = query_sum.list();
        model.addAttribute("categoryInsert", cate);
        return "product/insert";
    }
    
    @RequestMapping (value ="trangchu", method = RequestMethod.GET)
    public String trangchu(ModelMap model, HttpSession httpSession){
        cart = (HashMap<Integer, Item>) httpSession.getAttribute("cart");
        if(cart == null){
            System.out.println("Gio hang chua dc tao");
            //Chua co gio hang, khoi tao gio hang
            cart = new HashMap<>();
            //add lai vao httpsession
            httpSession.setAttribute("cart", cart);
        }
        Session session = factory.getCurrentSession();
        String hql = "FROM Product"; // lấy hết các tài khoản ra
        Query query = session.createQuery(hql);
        List<Product> list = query.list();
        model.addAttribute("products", list);
        return "product/trangchu";
    }
    //2. khi click insert thì chạy hàm này
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public String insert(ModelMap model, @ModelAttribute("product") Product product) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(product); // 
            t.commit();  // 2. commit nếu thêm thành công
            model.addAttribute("message", "Thêm mới thành công !");
        } catch (Exception e) {
            t.rollback();  // 3. rollback nếu thêm thất bại
            model.addAttribute("message", "Thêm mới thất bại !");
        } finally {
            session.close();
        }      
        return "redirect:index.htm?page=1"; //4. qua trang index hiện các tài khoản ra
    }
    
    @RequestMapping("update")
    public String update(ModelMap model, @RequestParam("productID") int productID) {
        Session session = factory.getCurrentSession();
        Product product = (Product) session.get(Product.class, productID);
         model.addAttribute("product", product);
        return "product/update";
    }

    //2. khi click insert thì chạy hàm này
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(ModelMap model, @ModelAttribute("product") Product product) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(product); // 1. thêm 1 tài khoản vào
            t.commit();  // 2. commit nếu thêm thành công
            model.addAttribute("message", "Update thành công !");
        } catch (Exception e) {
            t.rollback();  // 3. rollback nếu thêm thất bại
            model.addAttribute("message", "Update thất bại !");
        } finally {
            session.close();
        }      
        return "redirect:index.htm?page=1"; //4. qua trang index hiện các tài khoản ra
    }

    @RequestMapping("delete")
    public String delete(ModelMap model, @RequestParam("productID") int productID) {
        Session session = factory.getCurrentSession();
        Product product = (Product) session.get(Product.class, productID);
        session.delete(product);       
        return "redirect:index.htm?page=1";
    }
}
