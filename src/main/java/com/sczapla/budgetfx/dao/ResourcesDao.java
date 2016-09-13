/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.dao;

import com.sczapla.budgetfx.enums.TransactionType;
import com.sczapla.budgetfx.model.Resources;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Tomek
 */
public class ResourcesDao extends Dao{
    
    public List<Object[]> getResourcesCategoryChart(Date datefrom, Date dateTo, TransactionType type){
        StringBuilder hqlQuery = new StringBuilder();
        hqlQuery.append("select ext.name, sum(ex.amount) from Resources as ex join ex.category as ext ");
        hqlQuery.append("where (ex.date >= :dateFrom and ex.date <= :dateTo) ");
        if(type != null){
            hqlQuery.append("and ex.transactionType = :type ");
        }
        hqlQuery.append("group by ex.category ");
        Query query = getEntityManager().createQuery(hqlQuery.toString());
        query.setParameter("dateFrom", datefrom);
        query.setParameter("dateTo", dateTo);
        if(type != null){
            query.setParameter("type", type);
        }
        return query.getResultList();
    }
    
    public BigDecimal getSumResources(Date datefrom, Date dateTo, TransactionType type){
        StringBuilder hqlQuery = new StringBuilder();
        hqlQuery.append("select sum(ex.amount) from Resources as ex ");
        hqlQuery.append("where (ex.date >= :dateFrom and ex.date <= :dateTo) ");
        if(type != null){
            hqlQuery.append("and ex.transactionType = :type ");
        }
        Query query = getEntityManager().createQuery(hqlQuery.toString());
        query.setParameter("dateFrom", datefrom);
        query.setParameter("dateTo", dateTo);
        if(type != null){
            query.setParameter("type", type);
        }
        return (BigDecimal) query.getSingleResult();
    }
    
    public void saveResource(Resources resource){
       getEntityManager().getTransaction().begin();
       getEntityManager().persist(resource);
       getEntityManager().flush();
       getEntityManager().getTransaction().commit();
    }
}
