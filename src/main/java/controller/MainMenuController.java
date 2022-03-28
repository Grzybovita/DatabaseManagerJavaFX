package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import pokermanagerapp.MainPokerManager;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button newPlayerButton;

    @FXML
    private Button newTournamentButton;

    @FXML
    private Button searchTournamentsButton;

    @FXML
    private Button addPlayerToTournamentButton;

    @FXML
    private Button editPlayerButton;

    @FXML
    private AnchorPane leftPane;

    @FXML
    private AnchorPane rightPane;


    public void initialize() {
        newPlayerButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    AnchorPane newPane = FXMLLoader.load(this.getClass().getResource("/NewPlayer.fxml"));
                    if (!MainPokerManager.isNewPlayerTabVisible) {
                        rightPane.getChildren().setAll(newPane);
                        MainPokerManager.isNewPlayerTabVisible = true;
                        MainPokerManager.isNewTournamentTabVisible = false;
                        MainPokerManager.isSearchTournamentsTabVisible = false;
                        MainPokerManager.isAddPlayerToTournamentTabVisible = false;
                        MainPokerManager.isEditPlayerTabVisible = false;

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        newTournamentButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    AnchorPane newPane = FXMLLoader.load(this.getClass().getResource("/NewTournament.fxml"));
                    if (!MainPokerManager.isNewTournamentTabVisible) {
                        rightPane.getChildren().removeAll();
                        rightPane.getChildren().setAll(newPane);
                        MainPokerManager.isNewTournamentTabVisible = true;
                        MainPokerManager.isNewPlayerTabVisible = false;
                        MainPokerManager.isSearchTournamentsTabVisible = false;
                        MainPokerManager.isAddPlayerToTournamentTabVisible = false;
                        MainPokerManager.isEditPlayerTabVisible = false;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        searchTournamentsButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    AnchorPane newPane = FXMLLoader.load(this.getClass().getResource("/SearchTournaments.fxml"));
                    if (!MainPokerManager.isSearchTournamentsTabVisible) {
                        rightPane.getChildren().removeAll();
                        rightPane.getChildren().setAll(newPane);
                        MainPokerManager.isSearchTournamentsTabVisible = true;
                        MainPokerManager.isNewPlayerTabVisible = false;
                        MainPokerManager.isNewTournamentTabVisible = false;
                        MainPokerManager.isAddPlayerToTournamentTabVisible = false;
                        MainPokerManager.isEditPlayerTabVisible = false;

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        addPlayerToTournamentButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    AnchorPane newPane = FXMLLoader.load(this.getClass().getResource("/AddPlayerToTournament.fxml"));
                    if (!MainPokerManager.isAddPlayerToTournamentTabVisible) {
                        rightPane.getChildren().removeAll();
                        rightPane.getChildren().setAll(newPane);
                        MainPokerManager.isAddPlayerToTournamentTabVisible = true;
                        MainPokerManager.isNewPlayerTabVisible = false;
                        MainPokerManager.isNewTournamentTabVisible = false;
                        MainPokerManager.isSearchTournamentsTabVisible = false;
                        MainPokerManager.isEditPlayerTabVisible = false;

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        editPlayerButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    AnchorPane newPane = FXMLLoader.load(this.getClass().getResource("/EditPlayer.fxml"));
                    if (!MainPokerManager.isNewPlayerTabVisible) {
                        rightPane.getChildren().setAll(newPane);
                        MainPokerManager.isEditPlayerTabVisible = true;
                        MainPokerManager.isNewPlayerTabVisible = false;
                        MainPokerManager.isNewTournamentTabVisible = false;
                        MainPokerManager.isSearchTournamentsTabVisible = false;
                        MainPokerManager.isAddPlayerToTournamentTabVisible = false;

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

