package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Tournament;
import pokermanagerapp.DBService;
import pokermanagerapp.MainPokerManager;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchTournamentsController implements Initializable {

    @FXML
    private AnchorPane searchTournamentsPane;

    @FXML
    private TextField nameTextArea;

    @FXML
    private TextField buyinTextArea;

    @FXML
    private Button closeNewTournamentTabButton;

    @FXML
    private DatePicker dateDateBox;

    @FXML
    private TableView<Tournament> tableTableView;

    @FXML
    private TableColumn<Tournament, String> nameColumn;

    @FXML
    private TableColumn<Tournament, String> dateColumn;

    @FXML
    private TableColumn<Tournament, Integer> buyinColumn;

    @FXML
    private TableColumn<Tournament, Integer> stackColumn;

    @FXML
    private TableColumn<Tournament, Integer> blindsColumn;

    @FXML
    private TableColumn<Tournament, Integer> guaranteedColumn;

    ObservableList<Tournament> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        oblist = DBService.getTournamentListFromDB();

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        buyinColumn.setCellValueFactory(new PropertyValueFactory<>("buyin"));
        stackColumn.setCellValueFactory(new PropertyValueFactory<>("stack"));
        blindsColumn.setCellValueFactory(new PropertyValueFactory<>("blinds"));
        guaranteedColumn.setCellValueFactory(new PropertyValueFactory<>("guaranteed"));
        tableTableView.setItems(oblist);

        closeNewTournamentTabButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                searchTournamentsPane.setVisible(false);
                MainPokerManager.isSearchTournamentsTabVisible = false;
            }
        });
    }
}
