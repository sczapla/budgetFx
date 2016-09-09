/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Tomek
 */
public class ExpenseDao extends Dao{
    
    public List<Object[]> getExpenseCategoryChart(Date datefrom, Date dateTo){
        Query query = getEntityManager().createQuery("select ext.name, sum(ex.amount) from Expenses as ex join ex.type as ext "
                + "group by ex.type");
        return query.getResultList();
    }
}
