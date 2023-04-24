package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import pokermanagerapp.MainPokerManager;
import pokermanagerapp.TournamentDAO;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class NewTournamentController {

    @FXML
    private AnchorPane newTournamentPane;

    @FXML
    private TextField nameTextArea;

    @FXML
    private TextField buyinTextArea;

    @FXML
    private TextField stackTextArea;

    @FXML
    private TextField blindsTextArea;

    @FXML
    private TextField guaranteedTextArea;

    @FXML
    private Button addNewTournamentButton;

    @FXML
    private Button closeNewTournamentTabButton;

    @FXML
    private DatePicker dateDateBox;

    @FXML
    private ChoiceBox<String> leagueChoiceBox;

    private final String[] choice = {"YES", "NO"};

    public void initialize () {
       leagueChoiceBox.getItems().addAll(choice);

        closeNewTournamentTabButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!nameTextArea.getCharacters().isEmpty() || !buyinTextArea.getCharacters().isEmpty()
                        || !stackTextArea.getCharacters().isEmpty() || !blindsTextArea.getCharacters().isEmpty()
                        || !guaranteedTextArea.getCharacters().isEmpty())   {
                    String text = "You have unsaved changes! \n" +
                            "Are you sure you want to close?";
                    Alert alert = new Alert(Alert.AlertType.NONE, text, ButtonType.YES, ButtonType.NO);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        newTournamentPane.setVisible(false);
                        MainPokerManager.isNewTournamentTabVisible = false;
                    }
                } else {
                    newTournamentPane.setVisible(false);
                    MainPokerManager.isNewTournamentTabVisible = false;
                }
            }
        });

        addNewTournamentButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = nameTextArea.getCharacters().toString();
                String date = "";
                if (!(dateDateBox.getValue() == null)) {
                    date = dateDateBox.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                }
                String buyin = buyinTextArea.getCharacters().toString();
                String stack = stackTextArea.getCharacters().toString();
                String blinds = blindsTextArea.getCharacters().toString();
                String guaranteed = guaranteedTextArea.getCharacters().toString();
                boolean league = false;
                if (!(leagueChoiceBox.getValue() == null)) {
                    league = leagueChoiceBox.getValue().equals("YES");
                }
                TournamentDAO tournamentDAO = new TournamentDAO();

                if (name.equals("")){
                    Alert alert = new Alert(Alert.AlertType.NONE, "Please enter name!", ButtonType.OK);
                    alert.showAndWait();
                } else if (date.equals("")){
                    Alert alert = new Alert(Alert.AlertType.NONE, "Please enter date!", ButtonType.OK);
                    alert.showAndWait();
                } else if (buyin.equals("")){
                    Alert alert = new Alert(Alert.AlertType.NONE, "Please enter buy-in!", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    try {
                        tournamentDAO.insertRecord(name, date, buyin, stack, blinds, guaranteed, league);
                        Alert alert = new Alert(Alert.AlertType.NONE, "New tournament has been added!", ButtonType.OK);
                        alert.showAndWait();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }

}


