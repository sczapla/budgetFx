/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.dao;

import com.sczapla.budgetfx.model.Expenses;
import java.math.BigDecimal;
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
                + "where ex.date >= :dateFrom and ex.date <= :dateTo "
                + "group by ex.type");
        query.setParameter("dateFrom", datefrom);
        query.setParameter("dateTo", dateTo);
        return query.getResultList();
    }
    
    public BigDecimal getSumExpense(Date datefrom, Date dateTo){
        Query query = getEntityManager().createQuery("select sum(ex.amount) from Expenses as ex "
                + "where ex.date >= :dateFrom and ex.date <= :dateTo ");
        query.setParameter("dateFrom", datefrom);
        query.setParameter("dateTo", dateTo);
        return (BigDecimal) query.getSingleResult();
    }
    
    public void saveExpense(Expenses expense){
       getEntityManager().persist(expense);
    }
}
