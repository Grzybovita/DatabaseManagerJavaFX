package pokermanagerapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://sql11.freemysqlhosting.net/sql11481898?useSSL=false",
                "sql11481898", "pWSRq1YQbq");

        /*Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokermanager?useSSL=false",
                            "root", "admin");*/

        return connection;
    }
}
