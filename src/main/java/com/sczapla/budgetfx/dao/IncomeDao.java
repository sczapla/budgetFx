/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.dao;

import com.sczapla.budgetfx.model.Expenses;
import com.sczapla.budgetfx.model.Incomes;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Query;

/**
 *
 * @author Tomek
 */
public class IncomeDao extends Dao {

    public BigDecimal getSumIncome(Date datefrom, Date dateTo) {
        Query query = getEntityManager().createQuery("select sum(inc.amount) from Incomes as inc "
                + "where inc.date >= :dateFrom and inc.date <= :dateTo ");
        query.setParameter("dateFrom", datefrom);
        query.setParameter("dateTo", dateTo);
        return (BigDecimal) query.getSingleResult();
    }
    
    public void saveIncome(Incomes income){
       getEntityManager().persist(income);
    }
}
