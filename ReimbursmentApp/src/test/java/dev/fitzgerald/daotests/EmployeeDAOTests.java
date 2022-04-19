package dev.fitzgerald.daotests;

//import dev.fitzgerald.data.EmployeeDAO;
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
        Employee johnny = employeeDAO.getEmployeeById(1);
        Assertions.assertEquals(johnny.getFName(), "Mike");
    }

    @Test
    @Order(3)
    void updateEmployee(){
        testEmployee.setFname("Mark");
        Assertions.assertTrue(employeeDAO.updateEmployee(testEmployee));
    }
}
