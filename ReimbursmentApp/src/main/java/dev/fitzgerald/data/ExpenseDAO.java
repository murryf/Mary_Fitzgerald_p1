package dev.fitzgerald.data;

import dev.fitzgerald.entities.Expense;

import java.util.List;

public interface ExpenseDAO {

    //Create
    Expense createExpense(Expense expense);
    //Read
    Expense getExpenseById(int id);
    List<Expense> getAllApprovedExpenses();
    List<Expense> getAllDeniedExpenses();
    List<Expense> getAllPendingExpenses();
    List<Expense> getAllExpenses();
    //update
    Expense updateExpense(Expense expense);
    //delete
    boolean deleteExpense(int id);
}
