package dev.fitzgerald.tests.service;

import dev.fitzgerald.tests.entities.Expense;

import java.util.List;

public interface ExpenseService {

    //Create
    Expense createExpense(Expense expense);
    //Read
    Expense getExpenseById(int id);
    List<Expense> getAllExpenses();
    //update
    boolean updateExpense(Expense expense, int id);
    boolean updateExpenseStatus(int id, String status);
    //delete
    boolean deleteExpense(int id);
}