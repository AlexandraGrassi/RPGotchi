package ai151.grassi.controller;

import ai151.grassi.model.GameEngine;
import ai151.grassi.model.Gotchi;
import ai151.grassi.model.LivingEngine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private HBox gameWindow;
    @FXML
    private Label levelLabel, nameLabel, expLabel;
    @FXML
    private Label energyLabel, foodLabel, healthLabel, moodLabel, cleanLabel;
    @FXML
    private Label staminaLabel, agilityLabel, strengthLabel;

    private Gotchi myGotchi;
    private LivingEngine livingEngine;
    private GameEngine game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        livingEngine = new LivingEngine();
        myGotchi = new Gotchi("Meow",10,10, 10);
        game = new GameEngine();
        game.addGotchi(myGotchi);
        livingEngine.addGotchi(myGotchi);

        nameLabel.setText(myGotchi.getName());
        energyLabel.
                textProperty().bind(myGotchi.getEnergyProperty().asString().concat(" %"));
        foodLabel.
                textProperty().bind(myGotchi.getFoodProperty().asString().concat(" %"));
        healthLabel.
                textProperty().bind(myGotchi.getHealthProperty().asString().concat(" %"));
        moodLabel.
                textProperty().bind(myGotchi.getMoodProperty().asString().concat(" %"));
        cleanLabel.
                textProperty().bind(myGotchi.getCleanProperty().asString().concat(" %"));

        levelLabel.textProperty().bind(myGotchi.getLevelProperty().asString());
        expLabel.textProperty().bind(myGotchi.getExpProperty().asString());
        staminaLabel.textProperty().bind(myGotchi.getStaminaProperty().asString());
        agilityLabel.textProperty().bind(myGotchi.getAgilityProperty().asString());
        strengthLabel.textProperty().bind(myGotchi.getStrengthProperty().asString());
    }

    public void sendSleep(ActionEvent actionEvent) {
        game.sendSleep();
    }
    public void feed(ActionEvent actionEvent) {
        game.feed();
    }
    public void treat(ActionEvent actionEvent) {
        game.treat();
    }
    public void makeHappy(ActionEvent actionEvent) {
        game.makeHappy();
    }
    public void wash(ActionEvent actionEvent) {
        game.wash();
    }

    public void goBackToMenu(ActionEvent actionEvent) throws Exception{
        VBox pane = FXMLLoader.load(getClass().getResource("../view/menuWindow/menu.fxml"));
        gameWindow.getChildren().setAll(pane);
        livingEngine.deleteGotchi();
    }

    public void fight(ActionEvent actionEvent) throws Exception{
        VBox pane = FXMLLoader.load(getClass().getResource("../view/fightWindow/fight.fxml"));
        gameWindow.getChildren().setAll(pane);
        livingEngine.deleteGotchi();
    }

    // TODO сделать так, чтобы потребности не уменьшались, когда находишься вне основного окна
}