package ai151.grassi.controller;

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
    private Label levelLabel, nameLabel, experienceLabel;
    @FXML
    private Label energyLabel, foodLabel, healthLabel, moodLabel, cleanLabel;
    @FXML
    private Label staminaLabel, agilityLabel, strengthLabel;

    // готчи созданный по умолчанию
    private Gotchi myGotchi;
    private LivingEngine game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new LivingEngine();
        myGotchi = new Gotchi("Meow", 10, 10, 10);

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
        experienceLabel.textProperty().bind(myGotchi.getExperienceProperty().asString());
        staminaLabel.textProperty().bind(myGotchi.getStaminaProperty().asString());
        agilityLabel.textProperty().bind(myGotchi.getAgilityProperty().asString());
        strengthLabel.textProperty().bind(myGotchi.getStrengthProperty().asString());

        game.addGotchi(myGotchi);

    }

    public void sleep(ActionEvent actionEvent) {
        myGotchi.sleep();
    }
    public void eat(ActionEvent actionEvent) {
        myGotchi.eat();
    }
    public void treat(ActionEvent actionEvent) {
        myGotchi.treat();
    }
    public void makeHappy(ActionEvent actionEvent) {
        myGotchi.makeHappy();
    }
    public void wash(ActionEvent actionEvent) {
        myGotchi.wash();
    }

    public void goBackToMenu(ActionEvent actionEvent) throws Exception{
        VBox pane = FXMLLoader.load(getClass().getResource("../view/menuWindow/menu.fxml"));
        gameWindow.getChildren().setAll(pane);
        game.deleteGotchi();
    }

    public void fight(ActionEvent actionEvent) throws Exception{
        VBox pane = FXMLLoader.load(getClass().getResource("../view/fightWindow/fight.fxml"));
        gameWindow.getChildren().setAll(pane);
        game.deleteGotchi();
    }

    // TODO сделать так, чтобы потребности не уменьшались, когда находишься вне основного окна
}