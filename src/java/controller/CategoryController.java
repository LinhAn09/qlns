/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Categorys;
import java.util.List;
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
@RequestMapping("/category/")
public class CategoryController {
    //1. tao ra 1 doi tuong sessionFactory
    @Autowired
    SessionFactory factory;

    @RequestMapping("index")
    public String index(ModelMap model, @RequestParam("page") String getpage) {
     
        Session session = factory.getCurrentSession();
        String hql_sum = "Select count(categoryID)FROM Category";
        Query query_sum = session.createQuery(hql_sum);
        long tong = (long) query_sum.uniqueResult();

        long pages = Integer.parseInt(getpage), maxResult = 8;
        long indexrow = pages * maxResult - maxResult;
        long totalpage = (long) Math.ceil((double) tong / maxResult);

        String hql = "FROM Category"; // lấy hết các tài khoản ra
        Query query = session.createQuery(hql);
        query.setFirstResult((int) indexrow);
        query.setMaxResults((int) maxResult);
        List<Categorys> list = query.list();
        model.addAttribute("categorys", list); // lấy danh sách tài khoản list đưa vào thuộc tính users
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("pages", pages);
        return "category/index";
    }

    //1. trang insert hiện thị lên đầu tiên
    @RequestMapping(value = "insert", method = RequestMethod.GET)
    public String insert(ModelMap model) {
        model.addAttribute("category", new Categorys());
        return "category/insert";
    }

    //2. khi click insert thì chạy hàm này
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public String insert(ModelMap model, @ModelAttribute("category") Categorys category) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(category); // 1. thêm 1 tài khoản vào
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

    @RequestMapping("delete")
    public String delete(ModelMap model, @RequestParam("categoryID") int categoryID) {
        Session session = factory.getCurrentSession();
        Categorys category = (Categorys) session.get(Categorys.class, categoryID);
        session.delete(category);       
        return "redirect:index.htm?page=1";
    }
    
     @RequestMapping("update")
    public String update(ModelMap model, @RequestParam("categoryID") int categoryID) {
        Session session = factory.getCurrentSession();
        Categorys category = (Categorys) session.get(Categorys.class, categoryID);
         model.addAttribute("category", category);
        return "category/update";
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(ModelMap model, @ModelAttribute("category") Categorys category) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(category); // 
            t.commit();  // 2. commit nếu thêm thành công
            model.addAttribute("message", "update thanh cong !");
        } catch (Exception e) {
            t.rollback();  // 3. rollback nếu thêm thất bại
            model.addAttribute("message", "update that bai !");
        } finally {
            session.close();
        }      
        return "redirect:index.htm?page=1"; //4. qua trang index hiện các tài khoản ra
    }
}
