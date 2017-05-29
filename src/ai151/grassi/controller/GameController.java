package ai151.grassi.controller;

import ai151.grassi.model.GameEngine;
import ai151.grassi.model.Gotchi;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static ai151.grassi.model.GameConstants.MIN_VALUE;

public class GameController implements Initializable {
    @FXML
    private HBox gameWindow;
    @FXML
    private Label levelLabel, nameLabel, expLabel;
    @FXML
    private ProgressBar energyBar, foodBar, healthBar, moodBar, cleanBar;
    @FXML
    private Label staminaLabel, agilityLabel, strengthLabel;

    private static Gotchi myGotchi;
    private static GameEngine game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myGotchi = MenuController.getMyGotchi();

        nameLabel.setText(myGotchi.getName());

        energyBar.progressProperty().bind(myGotchi.getEnergyProperty());
        CustomProgressBar.setInitialColor(energyBar);
        CustomProgressBar.changeProgressBarColor(energyBar);

        foodBar.progressProperty().bind(myGotchi.getFoodProperty());
        CustomProgressBar.setInitialColor(foodBar);
        CustomProgressBar.changeProgressBarColor(foodBar);

        healthBar.progressProperty().bind(myGotchi.getHealthProperty());
        CustomProgressBar.setInitialColor(healthBar);
        CustomProgressBar.changeProgressBarColor(healthBar);

        cleanBar.progressProperty().bind(myGotchi.getCleanProperty());
        CustomProgressBar.setInitialColor(cleanBar);
        CustomProgressBar.changeProgressBarColor(cleanBar);

        moodBar.progressProperty().bind(myGotchi.getMoodProperty());
        CustomProgressBar.setInitialColor(moodBar);
        CustomProgressBar.changeProgressBarColor(moodBar);

        game = new GameEngine();
        game.addGotchi(myGotchi);

        levelLabel.textProperty().bind(myGotchi.getLevelProperty().asString());
        expLabel.textProperty().bind(myGotchi.getExpProperty().asString());
        staminaLabel.textProperty().bind(myGotchi.getStaminaProperty().asString());
        agilityLabel.textProperty().bind(myGotchi.getAgilityProperty().asString());
        strengthLabel.textProperty().bind(myGotchi.getStrengthProperty().asString());

        GameThread gameThread = new GameThread();
        gameThread.start();
    }

    public void goAway() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                myGotchi.setGone(true);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Важная информация!");
                alert.setHeaderText(null);
                alert.setContentText("Ваш питомец ушёл от вас :с");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent()) {
                    toMenu();
                }
            }
        });

    }

    public static Gotchi getMyGotchi() {
        return myGotchi;
    }

    public static GameEngine getGame() {
        return game;
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

    public void goBackToMenu(ActionEvent actionEvent) throws Exception {
        toMenu();
    }

    private void toMenu() {
        try {
            game.freezeLivingEngine();
            VBox pane = FXMLLoader.load(getClass().getResource("../view/menuWindow/menu.fxml"));
            gameWindow.getChildren().setAll(pane);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void fight(ActionEvent actionEvent) throws Exception{
        if(!myGotchi.isGone()) {
            game.freezeLivingEngine();
            VBox pane = FXMLLoader.load(getClass().getResource("../view/fightWindow/fight.fxml"));
            gameWindow.getChildren().setAll(pane);
        } else {
            goAway();
        }
    }

    class GameThread extends Thread {
        @Override
        public void run() {
            while (true) {
                if (myGotchi != null && myGotchi.isGone() == false) {
                    if(energyBar.getProgress() == MIN_VALUE || foodBar.getProgress() == MIN_VALUE || healthBar.getProgress() == MIN_VALUE) {
                        try {
                            game.freezeLivingEngine();
                            goAway();
                            Thread.sleep(15000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}

