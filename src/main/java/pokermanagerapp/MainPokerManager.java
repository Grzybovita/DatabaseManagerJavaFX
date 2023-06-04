package pokermanagerapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

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

        /*Player player = PlayerDAO.getPlayerById(1);
        Tournament tournament = TournamentDAO.getTournamentById(1);
        List<Player> playerList = PlayerDAO.getAllPlayers();
        List<Employee> employeeList = EmployeeDAO.getAllEmployees();
        List<Tournament> tournamentList = TournamentDAO.getAllTournaments();
        int personCount = Person.getCount();
        int playerCount = Player.getCount();
        System.out.println("breakpoint");*/

        //"zwykla" z frontendu, np dodawanie/usuwanie gracza z turnieju

        //asocjacja z atrybutem - miejsce gracza w turnieju
        //PlayerInTournamentDAO.insertRecord(1, 1, 5);
        //PlayerInTournamentDAO.updatePlace(5, 1, 1);

        //asocjacja kwalifikowana - dodawanie z kwalifikatorem i usuwanie
        //Player player = PlayerDAO.getPlayerById(1);
        //Contact phoneContact = new Contact("123456789");
        //player.addContact("phone", phoneContact);

        //Contact emailContact = new Contact("john.doe@example.com");
        //player.addContact("email", emailContact);

        // Pobieranie numeru telefonu gracza
        //Contact phone = player.getContact("phone");
        //System.out.println("Numer telefonu: " + phone.getValue());

        // Pobieranie adresu email gracza
        //Contact email = player.getContact("email");
        //System.out.println("Adres email: " + email.getValue());

        // Usuwanie adresu email gracza
        //player.removeContact("email");
        //System.out.println(player.getContact("email"));

        //kompozycja - Address w klasie Player - istnieje tylko dla konkretnego playera




        /*VBox mainBox = FXMLLoader.load(getClass().getResource("/AppFxml.fxml"));
        Scene scene = new Scene(mainBox);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();*/
    }
}
