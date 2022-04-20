package dev.fitzgerald.service;

import dev.fitzgerald.entities.Employee;
import dev.fitzgerald.entities.Expense;

import java.util.List;

public interface ReimbursementService {

    //Create
    Employee createEmployee(Employee employee);
    Expense createExpense(Expense expense);
    //read
    Employee getEmployeeById(int id);
    Expense getExpenseByID(int id);
    public List<Employee> getAllEmployees();

        //update
    Employee updateEmployee(Employee employee);
    Expense updateExpense(Expense expense);
    //delete
    boolean deleteEmployeeByID(int id);
    boolean deleteExpense(int id);

}
