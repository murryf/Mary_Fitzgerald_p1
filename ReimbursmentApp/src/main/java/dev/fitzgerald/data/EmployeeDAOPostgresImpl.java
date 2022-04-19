package dev.fitzgerald.data;

import dev.fitzgerald.entities.Employee;
import dev.fitzgerald.utilities.ConnectionUtil;

import java.sql.*;

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

    @Override
    public boolean deleteEmployeeById(int id) {
        return false;
    }

}
