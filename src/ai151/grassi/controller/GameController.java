package ai151.grassi.controller;

import ai151.grassi.model.Gotchi;
import ai151.grassi.model.Observer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable, Observer {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myGotchi = new Gotchi("Meow", 10, 10, 10);
        myGotchi.registerObserver(this);
        nameLabel.setText(myGotchi.getName());
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
    }

    public void fight(ActionEvent actionEvent) throws Exception{
        VBox pane = FXMLLoader.load(getClass().getResource("../view/fightWindow/fight.fxml"));
        gameWindow.getChildren().setAll(pane);
    }

    @Override
    public void updateMainInfo(int level, int experience) {
        levelLabel.setText("" + level);
        experienceLabel.setText("" + experience);
    }

    @Override
    public void updateEnergy(int energy) { energyLabel.setText(energy + "%"); }

    @Override
    public void updateFood(int food) {
        foodLabel.setText(food + "%");
    }

    @Override
    public void updateHealth(int health) { healthLabel.setText(health + "%"); }

    @Override
    public void updateMood(int mood) { moodLabel.setText(mood + "%");}

    @Override
    public void updateClean(int clean) { cleanLabel.setText(clean + "%");}

    @Override
    public void updateFightAbilities(int stamina, int agility, int strength) {
        staminaLabel.setText(stamina + "");
        agilityLabel.setText(agility + "");
        strengthLabel.setText(strength + "");
    }

}