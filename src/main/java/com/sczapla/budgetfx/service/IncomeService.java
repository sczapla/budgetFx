/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.service;

import com.sczapla.budgetfx.dao.IncomeDao;
import com.sczapla.budgetfx.dao.IncomeTypeDao;
import com.sczapla.budgetfx.model.Expenses;
import com.sczapla.budgetfx.model.IncomeType;
import com.sczapla.budgetfx.model.Incomes;
import com.sczapla.budgetfx.model.SettlementType;
import java.math.BigDecimal;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Tomek
 */
public class IncomeService {

    private IncomeDao incomeDao = new IncomeDao();
    private IncomeTypeDao incomeTypeDao = new IncomeTypeDao();

    public BigDecimal getSumExpense(Date dateFrom, Date dateTo) {
        if (dateFrom == null || dateTo == null) {
            return null;
        }
        return incomeDao.getSumIncome(dateFrom, dateTo);
    }

    public void saveIncome(Incomes income) {
        incomeDao.saveIncome(income);
    }

    public ObservableList<SettlementType> getAllIncomeType() {
        return FXCollections.observableArrayList(incomeTypeDao.getAllIncomeType());
    }
}
