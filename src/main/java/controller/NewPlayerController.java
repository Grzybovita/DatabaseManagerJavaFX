package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import pokermanagerapp.MainPokerManager;
import pokermanagerapp.PlayerDAO;
import java.sql.SQLException;

public class NewPlayerController {

    @FXML
    private AnchorPane newPlayerPane;

    @FXML
    private TextField nameTextArea;

    @FXML
    private TextField lastNameTextArea;

    @FXML
    private TextField nickTextArea;

    @FXML
    private TextField telTextArea;

    @FXML
    private TextField emailTextArea;

    @FXML
    private TextField addressTextArea;

    @FXML
    private TextField cityTextArea;

    @FXML
    private TextField postalCodeTextArea;

    @FXML
    private Button addNewPlayerButton;

    @FXML
    private Button closeNewPlayerTabButton;

    @FXML
    private RadioButton telNotificationsYesButton;

    @FXML
    private RadioButton emailNotificationsYesButton;

    public void initialize () {
        closeNewPlayerTabButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!nameTextArea.getCharacters().isEmpty() || !lastNameTextArea.getCharacters().isEmpty()
                    || !nickTextArea.getCharacters().isEmpty() || !telTextArea.getCharacters().isEmpty()
                    || !emailTextArea.getCharacters().isEmpty() || !addressTextArea.getCharacters().isEmpty()
                    || !cityTextArea.getCharacters().isEmpty() || !postalCodeTextArea.getCharacters().isEmpty())   {
                    String text = "You have unsaved changes! \n" +
                            "Are you sure you want to close?";
                    Alert alert = new Alert(Alert.AlertType.NONE, text, ButtonType.YES, ButtonType.NO);
                    alert.showAndWait();
                        if (alert.getResult() == ButtonType.YES) {
                            newPlayerPane.setVisible(false);
                            MainPokerManager.isNewPlayerTabVisible = false;
                    }
                } else {
                    newPlayerPane.setVisible(false);
                    MainPokerManager.isNewPlayerTabVisible = false;
                }
            }
        });

        addNewPlayerButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = nameTextArea.getCharacters().toString();
                String lastname = lastNameTextArea.getCharacters().toString();
                String nick = nickTextArea.getCharacters().toString();
                String telnumber = telTextArea.getCharacters().toString();
                String email = emailTextArea.getCharacters().toString();
                String address = addressTextArea.getCharacters().toString();
                String city = cityTextArea.getCharacters().toString();
                String postalcode = postalCodeTextArea.getCharacters().toString();
                boolean telpush = telNotificationsYesButton.isSelected();
                boolean emailpush = emailNotificationsYesButton.isSelected();
                PlayerDAO playerDAO = new PlayerDAO();

                if (name.equals("")){
                    Alert alert = new Alert(Alert.AlertType.NONE, "Please enter name!", ButtonType.OK);
                    alert.showAndWait();
                } else if (lastname.equals("")){
                    Alert alert = new Alert(Alert.AlertType.NONE, "Please enter last name!", ButtonType.OK);
                    alert.showAndWait();
                } else if (nick.equals("")){
                    Alert alert = new Alert(Alert.AlertType.NONE, "Please enter nick!", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    try {
                        playerDAO.insertRecord(name, lastname, nick, telnumber, email, address, city, postalcode, telpush, emailpush);
                        Alert alert = new Alert(Alert.AlertType.NONE, "New player has been added!", ButtonType.OK);
                        alert.showAndWait();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }

}

