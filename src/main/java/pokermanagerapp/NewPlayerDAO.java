package pokermanagerapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewPlayerDAO {
    private static final String DATABASE_URL = "jdbc:mysql://sql11.freemysqlhosting.net/sql11481898?useSSL=false";
    private static final String DATABASE_USERNAME = "sql11481898";
    private static final String DATABASE_PASSWORD = "pWSRq1YQbq";
    private static final String INSERT_QUERY = "INSERT INTO player (name, lastname, nick, telnumber, email, address, " +
                                            "city, postalcode, telpush, emailpush) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE player " +
                        "SET name = ?, lastname = ?, nick = ?, telnumber = ?, email = ?, address = ?, " +
                        "city = ?, postalcode = ?, telpush = ?, emailpush = ? " +
                        "WHERE id_player = ?;";


    public void insertRecord(String name, String lastname, String nick, String telnumber, String email, String address,
                             String city, String postalcode, boolean telpush, boolean emailpush) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, nick);
            preparedStatement.setString(4, telnumber);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, city);
            preparedStatement.setString(8, postalcode);
            preparedStatement.setBoolean(9, telpush);
            preparedStatement.setBoolean(10, emailpush);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void updateRecord(String name, String lastname, String nick, String telnumber, String email, String address,
                             String city, String postalcode, boolean telpush, boolean emailpush, int id) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, nick);
            preparedStatement.setString(4, telnumber);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, city);
            preparedStatement.setString(8, postalcode);
            preparedStatement.setBoolean(9, telpush);
            preparedStatement.setBoolean(10, emailpush);
            preparedStatement.setInt(11, id);

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
