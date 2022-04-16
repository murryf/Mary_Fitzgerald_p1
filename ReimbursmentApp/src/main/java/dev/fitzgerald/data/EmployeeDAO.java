package dev.fitzgerald.data;

import dev.fitzgerald.entities.Employee;

public interface EmployeeDAO {

    //create
    Employee createEmployee(Employee employee);
    //read
    Employee getEmployeeById(int id);
    //update
    Employee updateEmployee(Employee employee);
    //delete
    boolean deleteEmployee(int id);
}
