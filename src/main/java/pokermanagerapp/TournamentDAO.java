package pokermanagerapp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Address;
import model.Player;
import model.Tournament;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TournamentDAO {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pokermanager?useSSL=false" + "&" + "currentSchema=pokermanager";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "admin";
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

    public static ObservableList<Player> getPlayersListInTournament(int tournamentID){
        ObservableList<Player> resultList = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnector.getConnection();
            String tID = String.valueOf(tournamentID);
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM player p " +
                    "INNER JOIN player_tournament pt ON p.id = pt.id_player " +
                    "WHERE pt.id_tournament = %s".formatted(tID));
            while (rs.next()) {
                resultList.add(new Player(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("nick"),
                        rs.getString("telnumber"),
                        rs.getString("email"),
                        new Address(rs.getString("address"),
                                rs.getString("city"),
                                rs.getString("postalcode")),
                        rs.getBoolean("telpush"),
                        rs.getBoolean("emailpush")));
            }
            connection.close();
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

    public static Tournament getTournamentById(int id)
    {
        Tournament tournament = null;
        String query = "SELECT name, date, buyin, stack, blinds, guaranteed FROM tournament WHERE id=?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String date = resultSet.getString("date");
                int buyin = resultSet.getInt("buyin");
                int stack = resultSet.getInt("stack");
                int blinds = resultSet.getInt("blinds");
                int guaranteed = resultSet.getInt("guaranteed");
                tournament = new Tournament(id, name, date, buyin, stack, blinds, guaranteed);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return tournament;
    }

    public static List<Tournament> getAllTournaments() {
        List<Tournament> tournaments = new ArrayList<>();
        String query = "SELECT id, name, date, buyin, stack, blinds, guaranteed FROM tournament";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String date = resultSet.getString("date");
                int buyin = resultSet.getInt("buyin");
                int stack = resultSet.getInt("stack");
                int blinds = resultSet.getInt("blinds");
                int guaranteed = resultSet.getInt("guaranteed");
                Tournament tournament = new Tournament(id, name, date, buyin, stack, blinds, guaranteed);
                tournaments.add(tournament);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return tournaments;
    }

    public static Tournament getCheapestTournament(List<Tournament> tournamentList)
    {
        return tournamentList.stream()
                .min(Comparator.comparingInt(Tournament::getBuyin))
                .orElse(null);
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
