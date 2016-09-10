/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.service;

import com.sczapla.budgetfx.dao.ExpenseDao;
import com.sczapla.budgetfx.dao.ExpenseTypeDao;
import com.sczapla.budgetfx.model.ExpenseType;
import com.sczapla.budgetfx.model.Expenses;
import com.sczapla.budgetfx.model.SettlementType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author Tomek
 */
public class ExpenseService {
    
    private final ExpenseDao expenseDao = new ExpenseDao();
    private final ExpenseTypeDao expenseTypeDao = new ExpenseTypeDao();
    
    public ObservableList<PieChart.Data> getExpenseCategoryChart(Date dateFrom, Date dateTo){
        if(dateFrom == null || dateTo == null){
            return null;
        }
        ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList();
        List<Object[]> listResult = expenseDao.getExpenseCategoryChart(dateFrom, dateTo);
        Stream<PieChart.Data> ret = listResult.stream().map(el -> new PieChart.Data((String)el[0], ((BigDecimal)el[1]).doubleValue()));
        ret.forEach(el -> observableList.add(el));
        return observableList;
    }
    
    public BigDecimal getSumExpense(Date dateFrom, Date dateTo){
        if(dateFrom == null || dateTo == null){
            return null;
        }
        return expenseDao.getSumExpense(dateFrom, dateTo);
    }
    
    public void saveExpense(Expenses expense){
        expenseDao.saveExpense(expense);
    }
    
    public ObservableList<SettlementType> getAllExpenseType(){
        return FXCollections.observableArrayList(expenseTypeDao.getAllExpenseType());
    }
}
