/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.service;

import com.sczapla.budgetfx.dao.ExpenseDao;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author Tomek
 */
public class ExpenseService {
    
    private final ExpenseDao expenseDao = new ExpenseDao();
    
    public ObservableList<PieChart.Data> getExpenseCategoryChart(){
        ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList();
        List<Object[]> listResult = expenseDao.getExpenseCategoryChart(null, null);
        Stream<PieChart.Data> ret = listResult.stream().map(el -> new PieChart.Data((String)el[0], ((BigDecimal)el[1]).doubleValue()));
        ret.forEach(el -> observableList.add(el));
        return observableList;
    }
}
