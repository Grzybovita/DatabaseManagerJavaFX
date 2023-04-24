package pokermanagerapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Employee;
import model.Person;
import model.Player;
import model.Tournament;

import java.util.List;


public class MainPokerManager extends Application {
    public static boolean isLoginTabVisible = true;
    public static boolean isNewPlayerTabVisible = false;
    public static boolean isNewTournamentTabVisible = false;
    public static boolean isSearchTournamentsTabVisible = false;
    public static boolean isAddPlayerToTournamentTabVisible = false;
    public static boolean isEditPlayerTabVisible = false;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane mainPane = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        Player player = PlayerDAO.getPlayerById(1);
        Tournament tournament = TournamentDAO.getTournamentById(1);
        List<Player> playerList = PlayerDAO.getAllPlayers();
        List<Employee> employeeList = EmployeeDAO.getAllEmployees();
        List<Tournament> tournamentList = TournamentDAO.getAllTournaments();
        Tournament cheapestTournament = TournamentDAO.getCheapestTournament(tournamentList);
        int personCount = Person.getCount();
        int playerCount = Player.getCount();
        boolean isCheapestTournamentOlderThanOneYear = cheapestTournament.isOlderThanXYears();;
        System.out.println("breakpoint");


        /*VBox mainBox = FXMLLoader.load(getClass().getResource("/AppFxml.fxml"));
        Scene scene = new Scene(mainBox);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();*/
    }
}
