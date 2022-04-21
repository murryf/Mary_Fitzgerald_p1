package dev.fitzgerald.daotests;


import dev.fitzgerald.data.ExpenseDAOPostgresImpl;
import dev.fitzgerald.entities.Expense;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)// to run tests in order

public class ExpenseDAOTests {

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
        Expense testExpense2 = expenseDAO.getExpenseById(1);
        Assertions.assertNotNull(testExpense2);

    }

}
