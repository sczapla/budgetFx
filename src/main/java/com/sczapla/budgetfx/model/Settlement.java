/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Tomek
 */
public interface Settlement {

    String getName();
    String getDescription();
    User getUser();
    BigDecimal getAmount();
    Date getDate();
    SettlementType getType();
}
