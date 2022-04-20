package dev.fitzgerald.data;

import dev.fitzgerald.entities.Employee;
import dev.fitzgerald.utilities.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOPostgresImpl implements EmployeeDAO{

    /**
     * Method to construct a new employee object and assign the unique ID for the employee
     * @param employee the unsaved employee object that will return saved
     * @return the newly saved and updated employee object
     * */
    @Override
    public Employee createEmployee(Employee employee) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into employees values(default,?,?)";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,employee.getFName());
            ps.setString(2, employee.getLName());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            employee.setID(rs.getInt("employee_id"));
            return employee;

        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
    /**
     * Method to retrieve an employee by their unique ID
     * @param id the unique ID
     * @return the corresponding employee object
     * */
    @Override
    public Employee getEmployeeById(int id) {

        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from employees where employee_id = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            //Employee employee =

            return new Employee(rs.getString("fname"),
                    rs.getString("lname"), rs.getInt("employee_id"));

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method to return all the employees in a table
     * @return the List of employees
     * */
    @Override
    public List<Employee> getAllEmployees() {

        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from employees";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Employee> employees = new ArrayList<Employee>();
            while(rs.next()){
                Employee emp = new Employee("","", 0);
                emp.setID(rs.getInt("employee_id"));
                emp.setFname(rs.getString("fname"));
                emp.setLname(rs.getString("lname"));
                employees.add(emp);
            }
            return employees;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Method to update an employee first or last name.
     * @param employee the unsaved employee to be stored in the database
     * @returns the same employee if the database update worked, null if it did not
     * */
    public Employee updateEmployee(Employee employee) {
            try{
                Connection conn = ConnectionUtil.createConnection();
                String sql = "update employees set fname = ?, lname = ? where employee_id = ?";
                assert conn != null;
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, employee.getFName());
                ps.setString(2, employee.getLName());
                ps.setInt(3,employee.getEmployeeID());
                ps.execute();
                return employee;

            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
                return null;
            }

    }

    /**
     * Method to delete an employee by unique id
     * @param id the unique id number
     * @return the boolean status of execution. True if deleted, false if not deleted
     * */
    @Override
    public boolean deleteEmployeeById(int id) {

        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "delete from employees where employee_id = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            if(getEmployeeById(id) != null) {
                ps.execute();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

}
