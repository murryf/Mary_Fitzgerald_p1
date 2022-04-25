package dev.fitzgerald.service;

import dev.fitzgerald.data.EmployeeDAO;
import dev.fitzgerald.data.EmployeeDAOPostgresImpl;
import dev.fitzgerald.data.ExpenseDAO;
import dev.fitzgerald.data.ExpenseDAOPostgresImpl;
import dev.fitzgerald.entities.Employee;
import dev.fitzgerald.entities.Expense;

import java.util.List;

public class ReimbursementServiceImpl implements ReimbursementService{

    private final EmployeeDAO employeeDAO;
    private final ExpenseDAO expenseDAO =  new ExpenseDAOPostgresImpl();


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
    public Employee createEmployee(Employee employee) {return this.employeeDAO.createEmployee(employee);}



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
     * Handler to delete an employee by ID
     * @param id the id of the person to be deleted
     * @return the status of the deletion
     * */
    @Override
    public boolean deleteEmployeeByID(int id) {

        //Should filter out any employees that had associated expenses
        List<Expense> allExpenses = expenseDAO.getAllExpenses();
        for(Expense expense : allExpenses){
            if(expense.getEmployeeSource() == id){
                return false;
            }
        }
        return this.employeeDAO.deleteEmployeeById(id);
    }
}