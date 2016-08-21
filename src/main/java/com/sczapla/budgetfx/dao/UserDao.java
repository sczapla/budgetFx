/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.dao;

import com.sczapla.budgetfx.model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class UserDao {

    private EntityManager em;

    public UserDao() {
        em = Persistence.createEntityManagerFactory("budgetFx").createEntityManager();
    }

    public void saveOrUpdate(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public User getUserByLoginAndPass(String login) {
        return (User) em.createQuery("SELECT user FROM User user WHERE user.userName = :userName")
        .setParameter("userName", login).getSingleResult();
    }
}
