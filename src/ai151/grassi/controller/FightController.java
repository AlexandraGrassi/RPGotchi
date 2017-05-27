package ai151.grassi.controller;

import ai151.grassi.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

// TODO сверстать окно битвы  и начать заниматься логикой битвы
public class FightController implements Initializable {

    private GameEngine game;
    private LivingEngine livingEngine;
    private Fight fight;
    private Gotchi gotchi;
    private Monster monster;

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
        game = GameController.getGame();
        game.startBattle();
        fight = game.getBattle();
        monster = game.getCurMonster();

        gotchiName.setText(gotchi.getName());
        monsterName.setText(monster.getMonsterName());

        gotchiStrength.textProperty().bind(gotchi.getStrengthProperty().asString());
        gotchiAgility.textProperty().bind(gotchi.getAgilityProperty().asString());
        gotchiStamina.textProperty().bind(gotchi.getStaminaProperty().asString());

        monsterStrength.textProperty().bind(monster.getStaminaProperty().asString());
        monsterAgility.textProperty().bind(monster.getAgilityProperty().asString());
        monsterStamina.textProperty().bind(monster.getStrengthProperty().asString());

        gotchiHp.progressProperty().bind(gotchi.getHpProperty());
        monsterHp.progressProperty().bind(monster.getHpProperty());
    }

    public void goBackToGame(ActionEvent actionEvent) throws Exception {
        gotchi.setStamina(gotchi.getMaxFighterStamina());
        HBox pane = FXMLLoader.load(getClass().getResource("../view/gameWindow/game.fxml"));
        fightWindow.getChildren().setAll(pane);
    }

    public void attackLight(ActionEvent actionEvent) {
        gotchi.attackLight(monster);
        winBattle();
    }

    public void attackMedium(ActionEvent actionEvent) {
        gotchi.attackMedium(monster);
        winBattle();
    }

    public void attackHard(ActionEvent actionEvent) {
        gotchi.attackHard(monster);
        winBattle();
    }

    public void skipMove(ActionEvent actionEvent) {
        gotchi.skipMove();
    }

    public void winBattle() {
        if(monster.getHp() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Важная информация!");
            alert.setHeaderText(null);
            alert.setContentText("Ваш питомец выиграл!!!");
            alert.showAndWait();
        } else if(gotchi.getHp() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Важная информация!");
            alert.setHeaderText(null);
            alert.setContentText("Вы проиграли бой :с");
            alert.showAndWait();
        }

    }
}
