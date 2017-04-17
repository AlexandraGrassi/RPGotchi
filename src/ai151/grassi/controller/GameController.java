package ai151.grassi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable{
    @FXML
    private HBox gameWindow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void goBackToMenu(ActionEvent actionEvent) throws Exception{
        VBox pane = FXMLLoader.load(getClass().getResource("../view/menuWindow/menu.fxml"));
        gameWindow.getChildren().setAll(pane);
    }

    public void fight(ActionEvent actionEvent) throws Exception{
        VBox pane = FXMLLoader.load(getClass().getResource("../view/fightWindow/fight.fxml"));
        gameWindow.getChildren().setAll(pane);
    }

}