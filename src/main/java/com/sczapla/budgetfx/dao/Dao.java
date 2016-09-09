/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Tomek
 */
public class Dao {
    
    private EntityManager em;
    
    public Dao() {
        em = Persistence.createEntityManagerFactory("budgetFx").createEntityManager();
    }
    
    protected EntityManager getEntityManager(){
        return em;
    }
}
