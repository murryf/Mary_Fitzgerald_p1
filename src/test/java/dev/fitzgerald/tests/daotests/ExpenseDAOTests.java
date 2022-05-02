package dev.fitzgerald.tests.daotests;


import dev.fitzgerald.tests.data.ExpenseDAOPostgresImpl;
import dev.fitzgerald.tests.entities.Expense;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)// to run tests in order

class ExpenseDAOTests {

    static ExpenseDAOPostgresImpl expenseDAO = new ExpenseDAOPostgresImpl();
    static Expense testExpense = null;

    @Test
    @Order(1)
    void create_expense_test(){
        Expense car = new Expense(4,"A car rented on a busness trip", (float) 95.33);
        ExpenseDAOTests.testExpense = expenseDAO.createExpense(car);
        Assertions.assertNotEquals(0,testExpense.getExpenseID());
    }

    @Test
    @Order(2)
    void retrieve_expense_by_id(){
        Expense testExpense2 = expenseDAO.getExpenseById(6);
        Assertions.assertNotNull(testExpense2);

    }

    @Test
    @Order(3)
    void get_all_expenses(){
        List<Expense> expenses;
        expenses = expenseDAO.getAllExpenses();
        Assertions.assertNotNull(expenses);
    }




    @Test
    @Order(4)
    void updateEmployee() {
        testExpense.setDescription("Hotel Overnight");
        expenseDAO.updateExpense(testExpense, 1);
        Assertions.assertNotEquals(0, testExpense.getExpenseID());
    }

    @Test
    @Order(5)
    void update_employee_pending(){
        Expense expense = new Expense(6,"The Hilton 2 nights", 500);
        expense.setExpenseID(6);
        Assertions.assertTrue(expenseDAO.updateExpense(expense, 2));
    }
    @Test
    @Order(6)
    void update_employee_pending_bad(){
        Expense expense = new Expense(6,"The Hilton 2 nights", 500);
        expense.setExpenseID(6);
        Assertions.assertFalse(expenseDAO.updateExpense(expense, 5));
    }
}