/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Transactional
@Controller
public class AccountController {
    @Autowired
    SessionFactory factory;
    
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        model.addAttribute("user", new User());
        return "login";
    }
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    
    public String login(ModelMap model, @ModelAttribute("user") User user){
        /*AccountDAO accDAO = new AccountDAO();
        
        User acc = accDAO.findByUsername(user.getUsername());
        model.addAttribute("accLogin", acc);
        System.out.println(acc);*/
        
        
        Session session = factory.openSession();
         try {
            List<User> acc = new ArrayList<User>();
            acc = session.createQuery("from User where username=?").setParameter(0, user.getUsername()).list();
            if(acc.size() > 0){
                if(user.getPassword().equals(acc.get(0).getPassword())){
                    return "redirect:user/index.htm?page=1";
                }else{
                    model.addAttribute("messageLogin", "Fail");
                    
                    return "login";
                }
            }else{
                model.addAttribute("messageLogin", "Fail");
                return "login";
            }
            
        } catch (HibernateException e) {
             System.out.println(e);
        }
        return "login"; //4. qua trang index hiện các tài khoản ra
    }
}
