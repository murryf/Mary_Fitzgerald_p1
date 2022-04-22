package dev.fitzgerald.service;

import dev.fitzgerald.data.ExpenseDAOPostgresImpl;
import dev.fitzgerald.entities.Expense;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseDAOPostgresImpl expense;

    /**
     * Constructor for handler for an expense entity
     * @param expenseDAO the DAO object to be assigned to this handler
     * */
    public ExpenseServiceImpl(ExpenseDAOPostgresImpl expenseDAO) {
        this.expense = expenseDAO;
    }

    /**
     * Handler to create a new expense entity.
     * @param expense the expense being saved
     * @return the saved expense
     **/

    @Override
    public Expense createExpense(Expense expense) {
        return this.expense.createExpense(expense);
    }

    /**
     * Handler to get an expense by id number
     * @param id the id of the expense to be retrieved
     * */
    @Override
    public Expense getExpenseById(int id) {
        return expense.getExpenseById(id);
    }

    /**
     * Handler to get all expenses
     * @return the list of all expenses
     * */
    @Override
    public List<Expense> getAllExpenses() {
        return expense.getAllExpenses();
    }

    /**
     * Delegate the updating of an expense
     * @param expense the expense being handled
     * @return the now updated expense after being handled
     * */
    @Override
    public boolean updateExpense(Expense expense, int id) {
        return this.expense.updateExpense(expense, id);
    }

    /**
     * Delegates the delete function for an expense
     * @param id the id number of the expense to be deleted
     * */
    @Override
    public boolean deleteExpense(int id) {
        return expense.deleteExpense(id);
    }
}
