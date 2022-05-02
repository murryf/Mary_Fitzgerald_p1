package dev.fitzgerald.tests.service;

import dev.fitzgerald.tests.entities.Employee;

import java.util.List;

public interface ReimbursementService {

    //Create
    Employee createEmployee(Employee employee);

    //read
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();

    //update
    Employee updateEmployee(Employee employee);

    //delete
    boolean deleteEmployeeByID(int id);

}