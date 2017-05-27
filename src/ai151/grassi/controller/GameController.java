package ai151.grassi.controller;

import ai151.grassi.model.GameEngine;
import ai151.grassi.model.Gotchi;
import ai151.grassi.model.LivingEngine;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import static ai151.grassi.model.GameConstants.*;

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
    private static LivingEngine livingEngine;
    private static GameEngine game;

    private void setBarStyleClass(ProgressBar bar, String barStyleClass) {
        bar.getStyleClass().removeAll(barColorStyleClasses);
        bar.getStyleClass().add(barStyleClass);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        livingEngine = new LivingEngine();
        game = new GameEngine();
        myGotchi = MenuController.getMyGotchi();
        livingEngine.addGotchi(myGotchi);
        game.addGotchi(myGotchi);

        nameLabel.setText(myGotchi.getName());

        energyBar.progressProperty().bind(myGotchi.getEnergyProperty());
        setBarStyleClass(energyBar, GREEN_BAR);
        energyBar.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double progress = newValue == null ? 0 : newValue.doubleValue();
                if(progress == 0) {
                    livingEngine.deleteGotchi();
                    die();
                }
                if (progress < 0.2) {
                    setBarStyleClass(energyBar, RED_BAR);
                } else if (progress < 0.4) {
                    setBarStyleClass(energyBar, ORANGE_BAR);
                } else if (progress < 0.7) {
                    setBarStyleClass(energyBar, YELLOW_BAR);
                }
            }
        });

        foodBar.progressProperty().bind(myGotchi.getFoodProperty());
        setBarStyleClass(foodBar, GREEN_BAR);
        foodBar.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double progress = newValue == null ? 0 : newValue.doubleValue();
                if(progress == 0) {
                    livingEngine.deleteGotchi();
                    die();
                }
                if (progress < 0.2) {
                    setBarStyleClass(foodBar, RED_BAR);
                } else if (progress < 0.4) {
                    setBarStyleClass(foodBar, ORANGE_BAR);
                } else if (progress < 0.7) {
                    setBarStyleClass(foodBar, YELLOW_BAR);
                }
            }
        });

        healthBar.progressProperty().bind(myGotchi.getHealthProperty());
        setBarStyleClass(healthBar, GREEN_BAR);

        moodBar.progressProperty().bind(myGotchi.getMoodProperty());
        setBarStyleClass(moodBar, GREEN_BAR);
        moodBar.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double progress = newValue == null ? 0 : newValue.doubleValue();
                if (progress < 0.2) {
                    setBarStyleClass(moodBar, RED_BAR);
                } else if (progress < 0.4) {
                    setBarStyleClass(moodBar, ORANGE_BAR);
                } else if (progress < 0.7) {
                    setBarStyleClass(moodBar, YELLOW_BAR);
                }
            }
        });

        cleanBar.progressProperty().bind(myGotchi.getCleanProperty());
        setBarStyleClass(cleanBar, GREEN_BAR);
        cleanBar.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double progress = newValue == null ? 0 : newValue.doubleValue();
                if (progress < 0.2) {
                    setBarStyleClass(cleanBar, RED_BAR);
                } else if (progress < 0.4) {
                    setBarStyleClass(cleanBar, ORANGE_BAR);
                } else if (progress < 0.7) {
                    setBarStyleClass(cleanBar, YELLOW_BAR);
                }
            }
        });

        levelLabel.textProperty().bind(myGotchi.getLevelProperty().asString());
        expLabel.textProperty().bind(myGotchi.getExpProperty().asString());
        staminaLabel.textProperty().bind(myGotchi.getStaminaProperty().asString());
        agilityLabel.textProperty().bind(myGotchi.getAgilityProperty().asString());
        strengthLabel.textProperty().bind(myGotchi.getStrengthProperty().asString());
    }

    public void die() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Важная информация!");
        alert.setHeaderText(null);
        alert.setContentText("Ваш питомец умер :с");
        alert.showAndWait();
    }

    public static Gotchi getMyGotchi() {
        return myGotchi;
    }

    public static LivingEngine getLivingEngine() {
        return livingEngine;
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

    public void goBackToMenu(ActionEvent actionEvent) throws Exception{
        VBox pane = FXMLLoader.load(getClass().getResource("../view/menuWindow/menu.fxml"));
        gameWindow.getChildren().setAll(pane);
        livingEngine.deleteGotchi();
    }

    public void fight(ActionEvent actionEvent) throws Exception{
        livingEngine.deleteGotchi();
        VBox pane = FXMLLoader.load(getClass().getResource("../view/fightWindow/fight.fxml"));
        gameWindow.getChildren().setAll(pane);
    }


    // TODO сделать так, чтобы потребности не уменьшались, когда находишься вне основного окна
}