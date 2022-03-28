package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane LoginPane;

    @FXML
    private Button loginButton;

    @FXML
    private Button closeApplicationButton;

    @FXML
    private TextField loginTextArea;

    @FXML
    private TextField passwordTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loginButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle (ActionEvent actionEvent){
                String login = loginTextArea.getText();
                String password = passwordTextArea.getText();
                if (login.equals("xx") && password.equals("xx")) {
                    String text = "Logged as: " + login + "!";
                    Alert alert = new Alert(Alert.AlertType.NONE, text, ButtonType.OK);
                    alert.showAndWait();
                    VBox mainBox = null;
                    try {
                        mainBox = FXMLLoader.load(getClass().getResource("/AppFxml.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //get and close login tab
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    //create and show new tab with main app
                    Scene scene = new Scene(mainBox);
                    Stage newStage = new Stage();
                    newStage.setScene(scene);
                    newStage.setResizable(false);
                    newStage.show();

                } else {
                    String text = "Wrong username or password!";
                    Alert alert = new Alert(Alert.AlertType.NONE, text, ButtonType.OK);
                    alert.showAndWait();
                }
            }
        });

        closeApplicationButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle (ActionEvent actionEvent){
                String text = "Czy chcesz zakończyć program?";
                Alert alert = new Alert(Alert.AlertType.NONE, text, ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    Platform.exit();
                }
            }
    });



    }
}
