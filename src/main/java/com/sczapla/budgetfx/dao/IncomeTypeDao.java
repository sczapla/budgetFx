/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.dao;

import com.sczapla.budgetfx.model.IncomeType;
import java.util.List;

/**
 *
 * @author Tomek
 */
public class IncomeTypeDao extends Dao{
    public List<IncomeType> getAllIncomeType(){
        return getEntityManager().createQuery("from IncomeType").getResultList();
    }
}
