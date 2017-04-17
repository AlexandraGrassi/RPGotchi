package ai151.grassi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by AGrassi on 21.03.2017.
 */
public class RulesController implements Initializable {
    @FXML
    private VBox rulesWindow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void goBackToMenu(ActionEvent actionEvent) throws Exception {
        VBox pane = FXMLLoader.load(getClass().getResource("../view/menuWindow/menu.fxml"));
        rulesWindow.getChildren().setAll(pane);
    }
}
