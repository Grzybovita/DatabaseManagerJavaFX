package pokermanagerapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


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

        /*VBox mainBox = FXMLLoader.load(getClass().getResource("/AppFxml.fxml"));
        Scene scene = new Scene(mainBox);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();*/

    }
}
