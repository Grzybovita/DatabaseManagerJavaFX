package pokermanagerapp;

import model.Player;

import java.sql.*;

public class PlayerInTournamentDAO {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pokermanager?useSSL=false" + "&" + "currentSchema=pokermanager";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "admin";
    private static final String INSERT_QUERY = "INSERT INTO player_tournament (id_player, id_tournament) VALUES (?, ?);";
    private static final String INSERT_QUERY_WITH_PLACE = "INSERT INTO player_tournament (id_player, id_tournament, player_place) VALUES (?, ?, ?);";
    private static final String DELETE_QUERY = "DELETE FROM player_tournament\n" +
                                                "WHERE id_player = ? AND id_tournament = ?;";
    private static final String UPDATE_PLACE_QUERY = "UPDATE player_tournament SET player_place = ? WHERE id_player = ? AND id_tournament = ?;";



    public static void insertRecord(int id_player, int id_tournament) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, id_player);
            preparedStatement.setInt(2, id_tournament);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public static void insertRecord(int id_player, int id_tournament, int player_place) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY_WITH_PLACE)) {
            preparedStatement.setInt(1, id_player);
            preparedStatement.setInt(2, id_tournament);
            preparedStatement.setInt(3, player_place);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public static void deleteRecord(int id_player, int id_tournament) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, id_player);
            preparedStatement.setInt(2, id_tournament);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public static void updatePlace(int player_place, int id_player, int id_tournament) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PLACE_QUERY)) {
            preparedStatement.setInt(1, player_place);
            preparedStatement.setInt(2, id_player);
            preparedStatement.setInt(3, id_tournament);

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
