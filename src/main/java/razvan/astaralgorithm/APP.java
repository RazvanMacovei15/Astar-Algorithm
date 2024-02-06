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

/*
        Canvas canvas = new Canvas(1000, 1000);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Pane gridPane = Creator.createGridPane(GRID_SIZE);
        int[][] intGrid = Creator.getIntGrid();

        VBox vbox = new VBox();

        TextField startRow = new TextField();
        TextField startCol = new TextField();
        TextField endRow = new TextField();
        TextField endCol = new TextField();
        Button button = new Button("Start");
        vbox.getChildren().addAll(startRow, startCol, endRow, endCol, button);

        MyCell[][] grid = Creator.getGrid();

        button.setOnMouseClicked(event -> {
            int[] start = {Integer.parseInt(startRow.getText()), Integer.parseInt(startCol.getText())};
            int[] end = {Integer.parseInt(endRow.getText()), Integer.parseInt(endCol.getText())};

            Example.aStarSearch(intGrid, start, end);

            List<int[]> path = Example.getPathList();


        });
*/
