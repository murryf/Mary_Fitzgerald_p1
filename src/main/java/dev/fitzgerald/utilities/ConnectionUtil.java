package dev.fitzgerald.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection createConnection(){

        try {
            return  DriverManager.getConnection(System.getenv("REIMAPP"));

        } catch (SQLException e) {
            Logger.log(e.getMessage(), LoggerList.ERROR);
            //e.printStackTrace();
            return null;
        }

    }
}
