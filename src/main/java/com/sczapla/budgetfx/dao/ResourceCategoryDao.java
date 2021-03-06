/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.dao;

import com.sczapla.budgetfx.enums.TransactionType;
import com.sczapla.budgetfx.model.ResourceCategory;
import java.util.List;

/**
 *
 * @author Tomek
 */
public class ResourceCategoryDao extends Dao {
    
    public List<ResourceCategory> getAllResourcesCategory(TransactionType type){
        return getEntityManager().createQuery("from ResourceCategory as resCat where resCat.transactionType = :type")
                .setParameter("type", type).getResultList();
    }
    
     public void saveOrUpdate(ResourceCategory category) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(category);
        getEntityManager().getTransaction().commit();
    }
}
