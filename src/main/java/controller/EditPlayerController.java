package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.Player;
import pokermanagerapp.DBService;
import pokermanagerapp.MainPokerManager;
import pokermanagerapp.PlayerDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditPlayerController implements Initializable {

    @FXML
    private AnchorPane editPlayerPane;

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
    private RadioButton telNotificationsYesButton;

    @FXML
    private ToggleGroup telNotifications;

    @FXML
    private RadioButton telNotificationsNoButton;

    @FXML
    private RadioButton emailNotificationsYesButton;

    @FXML
    private ToggleGroup emailNotifications;

    @FXML
    private RadioButton emailNotificationsNoButton;

    @FXML
    private Button saveChangesButton;

    @FXML
    private Button closeEditPlayerTabButton;

    @FXML
    private ComboBox<Player> selectPlayerComboBox;

    ObservableList<Player> playerList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        playerList = DBService.getPlayerListFromDB();
        selectPlayerComboBox.setItems(playerList);

        Callback<ListView<Player>, ListCell<Player>> playerCellFactory =
                (new Callback<>() {
                    @Override
                    public ListCell<Player> call(ListView<Player> l) {
                        return new ListCell<>() {

                            @Override
                            protected void updateItem(Player item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item == null || empty) {
                                    setGraphic(null);
                                } else {
                                    setText(item.getNick() + " - " + item.getName() + " " + item.getLastname());
                                }
                            }
                        };
                    }
                });
        selectPlayerComboBox.setButtonCell(playerCellFactory.call(null));
        selectPlayerComboBox.setCellFactory(playerCellFactory);

        selectPlayerComboBox.valueProperty().addListener(new ChangeListener<Player>() {
            @Override
            public void changed(ObservableValue<? extends Player> observableValue, Player oldPlayer, Player newPlayer) {
                Player player = newPlayer;
                nameTextArea.setText(player.getName());
                lastNameTextArea.setText(player.getLastname());
                nickTextArea.setText(player.getNick());
                telTextArea.setText(player.getTelnumber());
                emailTextArea.setText(player.getEmail());
                addressTextArea.setText(player.getAddress());
                cityTextArea.setText(player.getCity());
                postalCodeTextArea.setText(player.getPostalcode());
                if (!player.isTelpush()){
                    telNotificationsNoButton.setSelected(true);
                }
                if (!player.isEmailpush()){
                    emailNotificationsNoButton.setSelected(true);
                }
            }
        });


        closeEditPlayerTabButton.setOnAction(new EventHandler<>() {
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
                        editPlayerPane.setVisible(false);
                        MainPokerManager.isEditPlayerTabVisible = false;
                    }
                } else {
                    editPlayerPane.setVisible(false);
                    MainPokerManager.isEditPlayerTabVisible = false;
                }
            }
        });

        saveChangesButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int id = selectPlayerComboBox.getSelectionModel().getSelectedItem().getId();
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
                        playerDAO.updateRecord(name, lastname, nick, telnumber, email, address, city, postalcode, telpush, emailpush, id);
                        Alert alert = new Alert(Alert.AlertType.NONE, "Player data has been updated!", ButtonType.OK);
                        alert.showAndWait();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }
}
