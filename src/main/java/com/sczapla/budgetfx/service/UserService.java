/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.service;

import com.sczapla.budgetfx.dao.UserDao;
import com.sczapla.budgetfx.model.User;
import org.mindrot.jbcrypt.BCrypt;


public class UserService {
    private final UserDao userDao = new UserDao();
    
    public boolean login(String login, String password){
        User user = userDao.getUserByLoginAndPass(login); 
        boolean ret = BCrypt.checkpw(password, user.getPassword());
        return ret;
    }
    
}
