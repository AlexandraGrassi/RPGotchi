package ai151.grassi.controller;

import ai151.grassi.model.Gotchi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private VBox rootPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void exitGame(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void loadGame(ActionEvent actionEvent) throws Exception {
        //VBox pane = FXMLLoader.load(getClass().getResource("../view/newGameWindow/newGame.fxml"));
        //myGotchi = new Gotchi("Meow",40,35, 30);
        HBox pane = FXMLLoader.load(getClass().getResource("../view/gameWindow/game.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void readRules(ActionEvent actionEvent) throws Exception {
        VBox pane = FXMLLoader.load(getClass().getResource("../view/rulesWindow/rules.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
