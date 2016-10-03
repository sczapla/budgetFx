/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.service;

import com.sczapla.budgetfx.dao.ResourcesDao;
import com.sczapla.budgetfx.dao.ResourceCategoryDao;
import com.sczapla.budgetfx.enums.TransactionType;
import com.sczapla.budgetfx.model.ResourceCategory;
import com.sczapla.budgetfx.model.Resources;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author Tomek
 */
public class ResourcesService {
    
    private final ResourcesDao resourcesDao = new ResourcesDao();
    private final ResourceCategoryDao resourcesCategoryDao = new ResourceCategoryDao();
    
    public ObservableList<PieChart.Data> getResourceCategoryChart(Date dateFrom, Date dateTo, TransactionType type){
        if(dateFrom == null || dateTo == null){
            return null;
        }
        ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList();
        List<Object[]> listResult = resourcesDao.getResourcesCategoryChart(dateFrom, dateTo, type);
        Stream<PieChart.Data> ret = listResult.stream().map(el -> new PieChart.Data((String)el[0], ((BigDecimal)el[1]).doubleValue()));
        ret.forEach(el -> observableList.add(el));
        return observableList;
    }
    
    public BigDecimal getSumExpense(Date dateFrom, Date dateTo, TransactionType type){
        if(dateFrom == null || dateTo == null){
            return null;
        }
        return resourcesDao.getSumResources(dateFrom, dateTo, type);
    }
    
    public void saveResource(Resources resource){
        resourcesDao.saveResource(resource);
    }
    
    public void saveResourceCategory(ResourceCategory resource){
        resourcesCategoryDao.saveOrUpdate(resource);
    }
    
    public ObservableList<ResourceCategory> getAllResourceType(TransactionType type){
        return FXCollections.observableArrayList(resourcesCategoryDao.getAllResourcesCategory(type));
    }
    
    public List<Resources> getResources(Date dateFrom, Date dateTo){
        List<Resources> resList = resourcesDao.getResources(dateFrom, dateTo);
        return resList;
    }
    
}
