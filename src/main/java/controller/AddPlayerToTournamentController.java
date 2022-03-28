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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.Player;
import model.Tournament;
import pokermanagerapp.DBService;
import pokermanagerapp.MainPokerManager;
import pokermanagerapp.NewPlayerInTournamentDAO;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddPlayerToTournamentController implements Initializable {

    @FXML
    private AnchorPane addPlayerToTournamentPane;

    @FXML
    private Button addNewPlayerButton;

    @FXML
    private Button removePlayerButton;

    @FXML
    private Button closeAddPlayerToTournamentButton;

    @FXML
    private ComboBox<Player> playerListComboBox;

    @FXML
    private ComboBox<Tournament> tournamentListComboBox;

    @FXML
    private TableView<Player> playersInTournamentTableView;

    @FXML
    private TableColumn<Player, String> nickColumn;

    @FXML
    private TableColumn<Player, String> nameColumn;

    @FXML
    private TableColumn<Player, String> lastNameColumn;


    ObservableList<Tournament> tournamentList = FXCollections.observableArrayList();
    ObservableList<Player> playerList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        tournamentList = DBService.getTournamentListFromDB();
        tournamentListComboBox.setItems(tournamentList);

        Callback<ListView<Tournament>, ListCell<Tournament>> tournamentCellFactory =
                (new Callback<ListView<Tournament>, ListCell<Tournament>>() {
            @Override
            public ListCell<Tournament> call(ListView<Tournament> l) {
                return new ListCell<Tournament>() {

                    @Override
                    protected void updateItem(Tournament item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getDate() + "    " + item.getName());
                        }
                    }
                };
            }
        });

        tournamentListComboBox.setButtonCell(tournamentCellFactory.call(null));
        tournamentListComboBox.setCellFactory(tournamentCellFactory);

        playerList = DBService.getPlayerListFromDB();
        playerListComboBox.setItems(playerList);

        Callback<ListView<Player>, ListCell<Player>> playerCellFactory =
                (new Callback<ListView<Player>, ListCell<Player>>() {
                    @Override
                    public ListCell<Player> call(ListView<Player> l) {
                        return new ListCell<Player>() {

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
        playerListComboBox.setButtonCell(playerCellFactory.call(null));
        playerListComboBox.setCellFactory(playerCellFactory);

        addNewPlayerButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               Player p = playerListComboBox.getValue();
               Tournament t = tournamentListComboBox.getValue();
               //check if such a record already exists
                try {
                    ResultSet rs = DBService.getPlayerTournamentSetFromDB();
                    boolean alreadyExists = false;
                    while (rs.next()){
                        if (rs.getInt("id_player") == p.getId() &&
                            rs.getInt("id_tournament") == t.getId()){
                                Alert alert = new Alert(Alert.AlertType.NONE, "This player is already signed!", ButtonType.OK);
                                alert.showAndWait();
                                alreadyExists = true;
                                break;
                        }
                    }
                    //if not exists, insert into database
                    if (!alreadyExists) {
                        //add new player
                        NewPlayerInTournamentDAO.insertRecord(p.getId(), t.getId());
                        //refresh ListView
                        playerList = DBService.getPlayersListInSpecTournamentFromDB(t.getId());
                        playersInTournamentTableView.setItems(playerList);
                        Alert alert = new Alert(Alert.AlertType.NONE, "New player has been added!", ButtonType.OK);
                        alert.showAndWait();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        nickColumn.setCellValueFactory(new PropertyValueFactory<>("nick"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));


        tournamentListComboBox.valueProperty().addListener(new ChangeListener<Tournament>() {
            @Override
            public void changed(ObservableValue<? extends Tournament> observableValue, Tournament oldValue, Tournament newValue) {
                playerList = DBService.getPlayersListInSpecTournamentFromDB(newValue.getId());
                playersInTournamentTableView.setItems(playerList);
            }
        });

        removePlayerButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int tournamentID = tournamentListComboBox.getValue().getId();
                int playerID = playersInTournamentTableView.getSelectionModel().getSelectedItem().getId();
                String text = "Are you sure?";
                Alert alert = new Alert(Alert.AlertType.NONE, text, ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    try {
                        //delete record from DB
                        NewPlayerInTournamentDAO.deleteRecord(playerID, tournamentID);
                        //refresh ListView
                        playerList = DBService.getPlayersListInSpecTournamentFromDB(tournamentID);
                        playersInTournamentTableView.setItems(playerList);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        closeAddPlayerToTournamentButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addPlayerToTournamentPane.setVisible(false);
                MainPokerManager.isAddPlayerToTournamentTabVisible = false;
            }
        });





    }
}
