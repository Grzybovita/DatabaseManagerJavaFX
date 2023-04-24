package pokermanagerapp;
import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pokermanager?useSSL=false" + "&" + "currentSchema=pokermanager";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "admin";
    private static final String INSERT_QUERY = "INSERT INTO player (name, lastname, nick, telnumber, email, address, " +
                                            "city, postalcode, telpush, emailpush) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE player " +
                        "SET name = ?, lastname = ?, nick = ?, telnumber = ?, email = ?, address = ?, " +
                        "city = ?, postalcode = ?, telpush = ?, emailpush = ? " +
                        "WHERE id = ?;";



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

    public static Player getPlayerById(int id)
    {
        Player player = null;
        String query = "SELECT name, lastname, nick, telnumber, email, address, city, postalcode, telpush, emailpush FROM player WHERE id=?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastname");
                String nick = resultSet.getString("nick");
                String telNumber = resultSet.getString("telnumber");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String postalCode = resultSet.getString("postalcode");
                boolean telPush = resultSet.getBoolean("telpush");
                boolean emailPush = resultSet.getBoolean("emailpush");
                player = new Player(id, name, lastName, nick, telNumber, email, address, city, postalCode, telPush, emailPush);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return player;
    }

    public static List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        String query = "SELECT id, name, lastname, nick, telnumber, email, address, city, postalcode, telpush, emailpush FROM player";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastname");
                String nick = resultSet.getString("nick");
                String telNumber = resultSet.getString("telnumber");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String postalCode = resultSet.getString("postalcode");
                boolean telPush = resultSet.getBoolean("telpush");
                boolean emailPush = resultSet.getBoolean("emailpush");
                Player player = new Player(id, name, lastName, nick, telNumber, email, address, city, postalCode, telPush, emailPush);
                players.add(player);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return players;
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
