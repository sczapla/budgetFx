/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.dao;

import com.sczapla.budgetfx.model.User;
import javax.persistence.NoResultException;

public class UserDao extends Dao {

    public void saveOrUpdate(User user) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(user);
        getEntityManager().getTransaction().commit();
    }

    public User getUserByLoginAndPass(String login) {
        try {
            return (User) getEntityManager().createQuery("SELECT user FROM User user WHERE user.userName = :userName")
                    .setParameter("userName", login).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
