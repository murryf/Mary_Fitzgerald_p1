package dev.fitzgerald.tests.daotests;

import dev.fitzgerald.tests.data.EmployeeDAOPostgresImpl;
import dev.fitzgerald.tests.entities.Employee;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)// to run tests in order

class EmployeeDAOTests {

    static EmployeeDAOPostgresImpl employeeDAO = new EmployeeDAOPostgresImpl();
    static Employee testEmployee = null;

    @Test
    @Order(1)
    void create_employee_test() {
        Employee Mike = new Employee("Mike", "Johnson", 0);
        Employee savedEmployee = employeeDAO.createEmployee(Mike);
        EmployeeDAOTests.testEmployee = savedEmployee;
        Assertions.assertNotEquals(0,savedEmployee.getEmployeeID());
    }

    @Test
    @Order(2)
    void getEmployeeByID(){
        Employee johnny = employeeDAO.getEmployeeById(1);
        Assertions.assertEquals( "Mike", johnny.getFName());
    }

    @Test
    @Order(3)
    void updateEmployee(){
        testEmployee.setFname("Grace");
        employeeDAO.updateEmployee(testEmployee);
        Assertions.assertNotEquals(0, testEmployee.getEmployeeID());
    }

    @Test
    @Order(4)
    void get_all_employees(){
        List<Employee> employees;
        employees = employeeDAO.getAllEmployees();
        Assertions.assertNotNull(employees);

    }


    @Test
    @Order(5)
    void deleteEmployee() {
        Assertions.assertTrue(employeeDAO.deleteEmployeeById(2));

    }
}