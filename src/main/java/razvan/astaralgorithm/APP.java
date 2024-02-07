package razvan.astaralgorithm;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;

public class APP extends Application {
    public static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
        APP.primaryStage = primaryStage;
        URL fxmlLocation = getClass().getResource("/razvan/astaralgorithm/StartingParameters.fxml");
        assert fxmlLocation != null;

        Parent root = null;
        try {
            root = javafx.fxml.FXMLLoader.load(fxmlLocation);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root, 200, 400);

        primaryStage.setTitle("A* Algorithm");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
