package ai151.grassi.controller;

import ai151.grassi.model.*;
import static ai151.grassi.model.GameConstants.*;

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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FightController implements Initializable {

    private GameEngine game;
    private Battle battle;
    private Gotchi gotchi;
    private Monster monster;

    final Lock lock = new ReentrantLock();
    final Condition win  = lock.newCondition();

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

        gotchi.setMaxHp();
        System.out.println(isGotchiLose());
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

        new Thread() {
            @Override
            public void run() {
                while (!isMonsterLose() || !isGotchiLose()) {
                    try {
                        lock.lock();
                        if(isMonsterLose()){
                            gotchiWin();
                            break;
                        } else if(isGotchiLose()) {
                            monsterWin();
                            break;
                        }
                    } finally{
                        lock.unlock();
                    }
                }
            }
        }.start();
    }

    public void goBackToGame(ActionEvent actionEvent) {
        gotchi.setLose(false);
        gotchi.setMaxHp();
        goBack();
    }

    private void goBack() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    gotchi.setStamina(gotchi.getMaxFighterStamina());
                    HBox pane = FXMLLoader.load(getClass().getResource("../view/gameWindow/game.fxml"));
                    fightWindow.getChildren().setAll(pane);
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void gotchiWin() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Важная информация!");
                alert.setHeaderText(null);
                alert.setContentText("Ваш питомец выиграл!!!");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent()) {
                    goBack();
                }
            }
        });
    }

    //TODO при проигрыше или выигрыше при закрытии окна переходить в главное окно
    //TODO пофиксить то, когда Готчи проиграл, то потом невозможно опять начать бой

    private  void monsterWin() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gotchi.setMaxHp();
                //gotchi.setLose(false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Важная информация!");
                alert.setHeaderText(null);
                alert.setContentText("Вы проиграли бой :с");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent()) {
                    goBack();
                }
            }
        });
    }

    public void attackLight(ActionEvent actionEvent) {
        gotchi.attackLight(monster);
        if(gotchi.isMoveDone() && !isMonsterLose()) {
            battle.beginBattle();
        }
    }

    public void attackMedium(ActionEvent actionEvent) {
        gotchi.attackMedium(monster);
        if(gotchi.isMoveDone() && !isMonsterLose()) {
            battle.beginBattle();
        }
    }

    public void attackHard(ActionEvent actionEvent) {
        gotchi.attackHard(monster);
        if(gotchi.isMoveDone() && !isMonsterLose()) {
            battle.beginBattle();
        }
    }

    public void skipMove(ActionEvent actionEvent) {
        gotchi.skipMove();
        battle.beginBattle();
    }

    public boolean isMonsterLose() {
        if(monster.getHp() == MIN_VALUE) {
            return true;
        }
        return false;
    }

    public boolean isGotchiLose() {
        if(gotchi.getHp() == MIN_VALUE) {
            return true;
        }
        return false;
    }


}
