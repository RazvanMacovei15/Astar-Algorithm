package razvan.astaralgorithm.Domain.Examples;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ExampleTwo extends Application {

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ExampleTwo.primaryStage = primaryStage;

        URL fxmlLocation = getClass().getResource("/razvan/astaralgorithm/MainScreen.fxml");
        assert fxmlLocation != null;
        Parent root = null;
        try {
            root = FXMLLoader.load(fxmlLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        double sceneWidth = 300; // Set your desired width
        double sceneHeight = 600; // Set your desired height

        primaryStage.setScene(new Scene(root, sceneWidth, sceneHeight));
        primaryStage.show();
    }




}
