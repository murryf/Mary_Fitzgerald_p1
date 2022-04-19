package dev.fitzgerald.data;

import dev.fitzgerald.entities.Employee;

public interface EmployeeDAO {

    //create
    Employee createEmployee(Employee employee);
    //read
    Employee getEmployeeById(int id);
    //update
    boolean updateEmployee(Employee employee);
    //delete
    boolean deleteEmployeeById(int id);
}
