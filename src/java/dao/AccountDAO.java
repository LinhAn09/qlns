/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author home
 */
public class AccountDAO {
    @Autowired
    private SessionFactory sessionFactory;
    public AccountDAO(){
        
    }
    public User findByUsername(String username){
        Session session = null;
        
        try {
            session = sessionFactory.openSession();
            List<User> user = new ArrayList<User>();
            user = session.createQuery("from User where username=?").setParameter(0, username).list();
            if(user.size() > 0){
                return user.get(0);
            }
            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
   public SessionFactory getSessionFactory(){
       return sessionFactory;
   }
}
