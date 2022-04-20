package dev.fitzgerald.daotests;

import dev.fitzgerald.data.EmployeeDAOPostgresImpl;
import dev.fitzgerald.entities.Employee;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)// to run tests in order

public class EmployeeDAOTests {

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
        Employee johnny = employeeDAO.getEmployeeById(2);
        Assertions.assertEquals(johnny.getFName(), "Monica");
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
    void deleteEmployee() {
        Assertions.assertTrue(employeeDAO.deleteEmployeeById(2));

    }
}
