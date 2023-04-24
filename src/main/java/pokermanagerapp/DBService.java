package pokermanagerapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Player;
import model.Tournament;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBService {

    public static ObservableList<Tournament> getTournamentListFromDB () {
        ObservableList<Tournament> resultList = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnector.getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM tournament");

            while (rs.next()) {
                resultList.add(new Tournament(rs.getInt("id"),rs.getString("name"),
                        rs.getString("date"), rs.getInt("buyin"),
                        rs.getInt("stack"), rs.getInt("blinds"),
                        rs.getInt("guaranteed")));
            }
            connection.close();
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

    public static ObservableList<Player> getPlayerListFromDB () {
        ObservableList<Player> resultList = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnector.getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM player");
            while (rs.next()) {
                resultList.add(new Player(rs.getInt("id"),rs.getString("name"),
                        rs.getString("lastname"), rs.getString("nick"),
                        rs.getString("telnumber"), rs.getString("email"),
                        rs.getString("address"),  rs.getString("city"),
                        rs.getString("postalcode"), rs.getBoolean("telpush"),
                        rs.getBoolean("emailpush")));
            }
            connection.close();
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

    public static ResultSet getPlayerTournamentSetFromDB () {

        try {
            Connection connection = DBConnector.getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM player_tournament");
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }




}
