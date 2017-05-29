package ai151.grassi;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

public class RPGotchi extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("view/menuWindow/menu.fxml"));

        Scene scene = new Scene(root, 683, 384);

        scene.getStylesheets().add("ai151/grassi/view/main.css");
        primaryStage.setTitle("RPGotchi");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Подтвердите действие");
                alert.setHeaderText("");
                alert.setContentText("Вы уверены, что хотите выйти?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    //Platform.exit();
                    System.exit(0);
                } else {
                    event.consume();
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
