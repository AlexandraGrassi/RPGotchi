package ai151.grassi.controller;

import ai151.grassi.model.Fight;
import ai151.grassi.model.GameEngine;
import ai151.grassi.model.Gotchi;
import ai151.grassi.model.Monster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

// TODO сверстать окно битвы  и начать заниматься логикой битвы
public class FightController implements Initializable {

    private GameEngine game;
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
        monster = game.getCurMonster();
        game.startBattle();
        fight = game.getBattle();

        gotchiName.setText(gotchi.getName());
        monsterName.setText(monster.getMonsterName());

        gotchiStrength.textProperty().bind(gotchi.getStaminaProperty().asString());
        gotchiAgility.textProperty().bind(gotchi.getAgilityProperty().asString());
        gotchiStamina.textProperty().bind(gotchi.getStrengthProperty().asString());

        monsterStrength.textProperty().bind(monster.getStaminaProperty().asString());
        monsterAgility.textProperty().bind(monster.getAgilityProperty().asString());
        monsterStamina.textProperty().bind(monster.getStrengthProperty().asString());

        //gotchiHp.accessibleTextProperty().bind(gotchi.getHpProperty().asString());
        //monsterHp.accessibleTextProperty().bind(monster.getHpProperty().asString());
    }

    public void goBackToGame(ActionEvent actionEvent) throws Exception {
        HBox pane = FXMLLoader.load(getClass().getResource("../view/gameWindow/game.fxml"));
        fightWindow.getChildren().setAll(pane);
    }

    public void attackLight(ActionEvent actionEvent) {
        gotchi.attackLight(monster);
        fight.battle();
    }

    public void attackMedium(ActionEvent actionEvent) {
        gotchi.attackMedium(monster);
        fight.battle();
    }

    public void attackHard(ActionEvent actionEvent) {
        gotchi.attackHard(monster);
        fight.battle();
    }

    public void skipMove(ActionEvent actionEvent) {
        gotchi.skipMove();
        fight.battle();
    }
}
