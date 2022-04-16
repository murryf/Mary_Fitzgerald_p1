package dev.fitzgerald.data;

import dev.fitzgerald.entities.Expense;

public interface ExpenseDAO {

    //Create
    Expense createExpense(Expense expense);
    //Read
    Expense getExpenseById(int id);
    //update
    Expense updateExpense(Expense expense);
    //delete
    boolean deleteExpense(int id);
}
