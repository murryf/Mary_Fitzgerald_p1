package dev.fitzgerald.tests.data;

import dev.fitzgerald.tests.entities.Expense;
import dev.fitzgerald.tests.utilities.ConnectionUtil;
import dev.fitzgerald.tests.utilities.Logger;
import dev.fitzgerald.tests.utilities.LoggerList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAOPostgresImpl implements ExpenseDAO {

    /**
     * Method to create a new expense in the database
     * @param expense the expense to be saved
     * @return the expense now updated
     * */
    @Override
    public Expense createExpense(Expense expense) {

        try {

            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into expenses values(default, ?, ?, ?, ?)";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, expense.getEmployeeSource());
            ps.setString(2, expense.getDescription());
            ps.setDouble(3,expense.getAmount());
            ps.setString(4, expense.getStatus());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            expense.setExpenseID(rs.getInt("expense_id"));
            return expense;
        } catch (SQLException | NullPointerException e) {
            Logger.log(e.getMessage(), LoggerList.ERROR);
            return null;
        }
    }


    /**
     * Method to fetch an expense by id
     * @param id the id being looked up
     * @return the corresponding expense
     * */
    @Override
    public Expense getExpenseById(int id) {

        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from expenses where expense_id = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Expense expense = new Expense();

            expense.setExpenseID(rs.getInt("expense_id"));
            expense.setEmployeeSource(rs.getInt("employee_src"));
            expense.setDescription(rs.getString("description"));
            expense.setAmount(rs.getFloat("amount"));
            expense.setStatus(rs.getString("approval"));

            return expense;

        } catch (SQLException e) {
            Logger.log(e.getMessage(), LoggerList.ERROR);
            return null;
        }
    }


    /**
     * Method to get all expenses
     * @return the list of all expenses
     * */
    @Override
    public List<Expense> getAllExpenses() {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from expenses";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Expense> expense = new ArrayList<>();
            while(rs.next()){
                Expense exp = new Expense(0,"", 0);
                exp.setExpenseID(rs.getInt("expense_id"));
                exp.setEmployeeSource(rs.getInt("employee_src"));
                exp.setDescription(rs.getString("description"));
                exp.setAmount(rs.getFloat("amount"));
                exp.setStatus(rs.getString("approval"));
                expense.add(exp);
            }
            return expense;

        } catch (SQLException e) {
            Logger.log(e.getMessage(), LoggerList.ERROR);
            return null;
        }


    }


    /**
     * Method to update an expense object if status is "Pending"
     * @param expense the unsaved expense data
     * @param id the id of the expense being updated
     * @return the representation of success or failure;
     * */
    @Override
    public boolean updateExpense(Expense expense, int id) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update expenses set description = ?, amount = ? where approval = 'Pending' and expense_id = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, expense.getDescription());
            ps.setDouble(2,expense.getAmount());
            ps.setInt(3, id);
            ps.execute();
            return true; // returns true if the prepared statement executes correctly

        } catch (SQLException | NullPointerException e) {
            Logger.log(e.getMessage(), LoggerList.ERROR);
            return false;
        }
    }

    /**
     * Method to update the expense status to either Approved or Denied
     * @param id the id of the expense to be updated
     * @param status the new status
     * @return the boolean representation of success or failure
     * */
    @Override
    public boolean updateExpenseStatus(int id, String status) {

        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update expenses set approval = ? where expense_id = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,status);
            ps.setInt(2,id);
            ps.execute();
            return true;

        } catch(SQLException | NullPointerException e) {
            Logger.log(e.getMessage(), LoggerList.ERROR);
            return false;
        }
    }

    /**
     * Method to delete an expense item only if it is pending
     * */
    @Override
    public boolean deleteExpense(int id) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "delete from expenses where expense_id = ? and approval = 'Pending'";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            if(getExpenseById(id) != null) {
                ps.execute();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e){
            Logger.log(e.getMessage(), LoggerList.ERROR);
            return false;
        }
    }
}