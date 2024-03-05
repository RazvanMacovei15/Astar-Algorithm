package razvan.astaralgorithm.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import razvan.astaralgorithm.APP;

import java.io.IOException;

public class SceneChange {
    public static void switchScene(String path, double width, double height){

        FXMLLoader loader = new FXMLLoader(SceneChange.class.getResource(path));

        Parent sceneRoot = null;
        try {
            sceneRoot = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(sceneRoot, width, height);
        Stage stage = APP.getPrimaryStage();
        // Get the Stage
        stage.setScene(scene);
        stage.show();
    }
}
