package razvan.astaralgorithm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import razvan.astaralgorithm.HelperClasses.Creator;

public class Test extends Application {

    private static final int GRID_SIZE = 10; // Adjust this based on your requirements

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(1000, 1000);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Pane gridPane = Creator.createGridPane(GRID_SIZE);

        Pane root = new Pane();
        root.getChildren().addAll(gridPane);

        Scene scene = new Scene(root, 1000, 1000);

        primaryStage.setTitle("Colored Grid with Drawing");
        primaryStage.setScene(scene);
        primaryStage.show();
        drawOnTopOfGrid(gc, GRID_SIZE);

    }

    private void drawOnTopOfGrid(GraphicsContext gc, int gridSize) {
        double cellWidth = 1000.0 / gridSize;
        double cellHeight = 1000.0 / gridSize;

        gc.setStroke(Color.RED);
        gc.setLineWidth(2.0);

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                double startX = col * cellWidth;
                double startY = row * cellHeight;
                double endX = startX + cellWidth;
                double endY = startY + cellHeight;

                gc.strokeLine(startX, startY, endX, endY);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
