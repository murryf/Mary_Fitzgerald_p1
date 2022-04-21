package dev.fitzgerald.service;

import dev.fitzgerald.data.EmployeeDAO;
import dev.fitzgerald.data.EmployeeDAOPostgresImpl;
import dev.fitzgerald.entities.Employee;
import dev.fitzgerald.entities.Expense;

import java.util.List;

public class ReimbursementServiceImpl implements ReimbursementService{

    private EmployeeDAO employeeDAO;


    /**
     * Method to create a reimbursement handler for employees
     * @param employeedao the DAO object to manipulate the data
     * */
    public ReimbursementServiceImpl(EmployeeDAOPostgresImpl employeedao){
        this.employeeDAO = employeedao;
    }

    /**
     * Handler to create a new Employee entity
     * @param employee the new employee entity
     * @return the saved employee entity
     * */
    @Override
    public Employee createEmployee(Employee employee) {
        Employee employee1 = this.employeeDAO.createEmployee(employee);
        return employee1;
    }

    /**
     * Handler to create an associated expense for the employee object
     * @param expense an expense to be saved to the employee
     * @return the saved expense
     * */
    @Override
    public Expense createExpense(Expense expense) {
        return null;
    }

    /**
     * Handler to get an employee by their id number
     * @param id the id to be searched
     * @return the employee once located
     * */
    @Override
    public Employee getEmployeeById(int id) {
        return this.employeeDAO.getEmployeeById(id);
    }

    /**
     * Handler to get an expense associated with an employee by id number
     * @param id the expense ID to be searched
     * @return the related expense
     * */
    @Override
    public Expense getExpenseByID(int id) {
        return null;
    }

    /**
     * Handler to get all employees
     * @return a list of all employees
     * */
    public List<Employee> getAllEmployees() {
        return this.employeeDAO.getAllEmployees();
    }

    /**
     * Method to update fname and lname fields of an employee
     * @param employee the unsaved employee data
     * @return the saved employee
     * */
    @Override
    public Employee updateEmployee(Employee employee) {
        return this.employeeDAO.updateEmployee(employee);
    }

    /**
     * Handler to update a related expense
     * @param expense the unsaved expense
     * @return the saved expense
     * */
    @Override
    public Expense updateExpense(Expense expense) {
        return null;
    }

    /**
     * Handler to delete an employee by ID
     * @param id the id of the person to be deleted
     * @return the status of the deletion
     * */
    @Override
    public boolean deleteEmployeeByID(int id) {
        return this.employeeDAO.deleteEmployeeById(id);
    }

    /**
     * Handler to delete an expense by ID
     * @param id the id of the expense
     * @return the status of the deletion
     * */
    @Override
    public boolean deleteExpense(int id) {
        return false;
    }
}
