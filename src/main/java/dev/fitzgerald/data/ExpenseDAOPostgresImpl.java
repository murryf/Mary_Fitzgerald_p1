package dev.fitzgerald.data;

import dev.fitzgerald.entities.Expense;
import dev.fitzgerald.utilities.ConnectionUtil;
import org.postgresql.util.PSQLException;

import java.security.Provider;
import java.sql.*;
import java.util.List;

public class ExpenseDAOPostgresImpl implements ExpenseDAO {
    @Override
    public Expense createExpense(Expense expense) {

        try {

            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into expenses values(default, ?, ?, ?, ?)";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, expense.getEmployeeSource());
            ps.setString(2, expense.getDescription());
            ps.setFloat(3,expense.getAmount());
            ps.setString(4, expense.getStatus());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            expense.setExpenseID(rs.getInt("expense_id"));
            return expense;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

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

            Expense expense = new Expense(); //default set to pending do not need to assign approval

            expense.setExpenseID(rs.getInt("expense_id"));
            expense.setEmployeeSource(rs.getInt("employee_src"));
            expense.setDescription(rs.getString("description"));
            expense.setAmount(rs.getFloat("amount"));

            return expense;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Expense> getAllApprovedExpenses() {
        return null;
    }

    @Override
    public List<Expense> getAllDeniedExpenses() {
        return null;
    }

    @Override
    public List<Expense> getAllPendingExpenses() {
        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return null;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return null;
    }

    @Override
    public boolean deleteExpense(int id) {
        return false;
    }
}
