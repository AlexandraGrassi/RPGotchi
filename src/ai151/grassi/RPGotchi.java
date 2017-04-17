package ai151.grassi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
