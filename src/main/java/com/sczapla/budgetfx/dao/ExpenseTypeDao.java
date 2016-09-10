/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.dao;

import com.sczapla.budgetfx.model.ExpenseType;
import java.util.List;

/**
 *
 * @author Tomek
 */
public class ExpenseTypeDao extends Dao {
    
    public List<ExpenseType> getAllExpenseType(){
        return getEntityManager().createQuery("from ExpenseType").getResultList();
    }
}
