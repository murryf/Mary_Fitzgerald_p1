package dev.fitzgerald.tests.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private ConnectionUtil(){}//cannot instantiate the connection utility

    public static Connection createConnection(){

        try {
            return  DriverManager.getConnection(System.getenv("REIMAPP"));

        } catch (SQLException e) {
            Logger.log(e.getMessage(), LoggerList.ERROR);

            return null;
        }
    }
}