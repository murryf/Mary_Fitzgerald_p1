package dev.fitzgerald.service;

import dev.fitzgerald.entities.Expense;

import java.util.List;

public interface ExpenseService {

    //Create
    Expense createExpense(Expense expense);
    //Read
    Expense getExpenseById(int id);
    List<Expense> getAllExpenses();
    //update
    Expense updateExpense(Expense expense);
    //delete
    boolean deleteExpense(int id);
}
