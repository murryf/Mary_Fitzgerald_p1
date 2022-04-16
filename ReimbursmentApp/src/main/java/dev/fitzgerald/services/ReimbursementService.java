package dev.fitzgerald.services;

import dev.fitzgerald.entities.Expense;

public interface ReimbursementService {

    //Create
    Expense createExpense(Expense expense);
    //read
    Expense getExpenseByID(int id);
    //update
    boolean updateExpense(Expense expense);
    //delete
    boolean deleteExpense(int id);
}
