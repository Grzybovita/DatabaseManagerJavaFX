package pokermanagerapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewTournamentDAO {
    private static final String DATABASE_URL = "jdbc:mysql://sql11.freemysqlhosting.net/sql11481898?useSSL=false";
    private static final String DATABASE_USERNAME = "sql11481898";
    private static final String DATABASE_PASSWORD = "pWSRq1YQbq";
    private static final String INSERT_QUERY = "INSERT INTO tournament (name, date, buyin, stack, blinds, guaranteed, " +
            "league) VALUES (?, ?, ?, ?, ?, ?, ?)";


    public void insertRecord(String name, String date, String buyin, String stack, String blinds, String guaranteed,
                             boolean league) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, buyin);
            preparedStatement.setString(4, stack);
            preparedStatement.setString(5, blinds);
            preparedStatement.setString(6, guaranteed);
            preparedStatement.setBoolean(7, league);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
