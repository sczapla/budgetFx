/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx.enums;

/**
 *
 * @author Tomek
 */
public enum TransactionType {
    EXPENSE("Wydatek"),
    INCOME("Przychód");

    private final String name;

    private TransactionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
