package dev.fitzgerald.service;

import dev.fitzgerald.data.ExpenseDAOPostgresImpl;
import dev.fitzgerald.entities.Expense;
import dev.fitzgerald.utilities.Logger;
import dev.fitzgerald.utilities.LoggerList;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseDAOPostgresImpl expense;
    private static final String DEFAULTSTATUS = "Pending";

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
        if(expense.getAmount() < 0){
            Logger.log("Negative value detected", LoggerList.WARNING);
            return null;
        } else {
            return this.expense.createExpense(expense);
        }
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
     * @param expense the expense information to update
     * @param id the id of the expense to be updated
     * @return the now updated expense after being handled
     * */
    @Override
    public boolean updateExpense(Expense expense, int id) {
        if(this.expense.getExpenseById(id).getStatus().compareTo(DEFAULTSTATUS) == 0) {
            return this.expense.updateExpense(expense, id);
        } else {
            return false;
        }
    }

    /**
     * Method to update the expense status
     * @param id the id to the expense being updated
     * @param status the new and final status
     * @return a success or failure of the execution
     * */
    @Override
    public boolean updateExpenseStatus(int id, String status) {
        if(expense.getExpenseById(id).getStatus().compareTo(DEFAULTSTATUS) == 0) {
            return this.expense.updateExpenseStatus(id, status);
        } else {
            return false;
        }
    }

    /**
     * Delegates the delete function for an expense
     * @param id the id number of the expense to be deleted
     * */
    @Override
    public boolean deleteExpense(int id) {
        if(expense.getExpenseById(id).getStatus().compareTo(DEFAULTSTATUS) != 0){
            return false;
        }
        return expense.deleteExpense(id);
    }
}