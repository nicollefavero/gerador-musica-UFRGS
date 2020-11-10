package nerdj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowHandler extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("nerdj.fxml"));
        primaryStage.setTitle("Nerdj");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }

    public void load() {
        launch();
    }
}
