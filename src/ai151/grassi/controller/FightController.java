package ai151.grassi.controller;

import ai151.grassi.model.*;

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
import java.util.ResourceBundle;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FightController implements Initializable {

    private GameEngine game;
    private Battle battle;
    private Gotchi gotchi;
    private Monster monster;

    final private Lock lock = new ReentrantLock();

    @FXML
    private VBox fightWindow;
    @FXML
    private Label gotchiName, gotchiStrength, gotchiAgility, gotchiStamina;
    @FXML
    private Label monsterName, monsterStrength, monsterAgility, monsterStamina;
    @FXML
    private ProgressBar gotchiHp, monsterHp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        gotchi = GameController.getMyGotchi();
        monster = GameController.getCurMonster();

        gotchiHp.progressProperty().bind(gotchi.getHpProperty());
        CustomProgressBar.setBarStyleClass(gotchiHp, CustomProgressBar.GREEN_BAR);
        CustomProgressBar.changeProgressBarColor(gotchiHp);

        monsterHp.progressProperty().bind(monster.getHpProperty());
        CustomProgressBar.setBarStyleClass(monsterHp, CustomProgressBar.GREEN_BAR);
        CustomProgressBar.changeProgressBarColor(monsterHp);

        gotchiName.setText(gotchi.getName());
        monsterName.setText(monster.getMonsterName());

        gotchiStrength.textProperty().bind(gotchi.getStrengthProperty().asString());
        gotchiAgility.textProperty().bind(gotchi.getAgilityProperty().asString());
        gotchiStamina.textProperty().bind(gotchi.getStaminaProperty().asString());

        monsterStrength.textProperty().bind(monster.getStrengthProperty().asString());
        monsterAgility.textProperty().bind(monster.getAgilityProperty().asString());
        monsterStamina.textProperty().bind(monster.getStaminaProperty().asString());

        battle = new Battle(gotchi,monster);

        new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    if(monster.isLose()){
                        gotchiWin();
                        break;
                    } else if(gotchi.isLose()) {
                        monsterWin();
                        break;
                    }
                } finally{
                    lock.unlock();
                }
            }
        }).start();
    }

    public void goBackToGame(ActionEvent actionEvent) {
        goBack();
    }

    private void goBack() {
        try {
            gotchi.setStamina(gotchi.getMaxFighterStamina());
            HBox pane = FXMLLoader.load(getClass().getResource("../view/gameWindow/game.fxml"));
            fightWindow.getChildren().setAll(pane);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void gotchiWin() {
        Platform.runLater(() -> {
            gotchi.setCountWins(gotchi.getCountWins() + 1);
            System.out.println(gotchi.getCountWins());
            gotchi.levelUp();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Важная информация!");
            alert.setHeaderText(null);
            alert.setContentText("Ваш питомец выиграл!!!");
            alert.showAndWait();
            goBack();
        });
    }

    private  void monsterWin() {
        Platform.runLater(() -> {
            gotchi.setMinExp();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Важная информация!");
            alert.setHeaderText(null);
            alert.setContentText("Вы проиграли бой :с");
            alert.showAndWait();
            goBack();
        });
    }

    public void attackLight(ActionEvent actionEvent) {
        gotchi.attackLight(monster);
        if(gotchi.isMoveDone() && !monster.isLose()) {
            battle.beginBattle();
        }
    }

    public void attackMedium(ActionEvent actionEvent) {
        gotchi.attackMedium(monster);
        if(gotchi.isMoveDone() && !monster.isLose()) {
            battle.beginBattle();
        }
    }

    public void attackHard(ActionEvent actionEvent) {
        gotchi.attackHard(monster);
        if(gotchi.isMoveDone() && !monster.isLose()) {
            battle.beginBattle();
        }
    }

    public void skipMove(ActionEvent actionEvent) {
        gotchi.skipMove();
        battle.beginBattle();
    }

}
