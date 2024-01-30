package razvan.astaralgorithm;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import razvan.astaralgorithm.Domain.Examples.Example;
import razvan.astaralgorithm.Domain.MyCell;
import razvan.astaralgorithm.HelperClasses.Creator;

import java.util.List;
import java.util.Map;

public class Test extends Application {

    private static final int GRID_SIZE = 10; // Adjust this based on your requirements

    @Override
    public void start(Stage primaryStage) {
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

            for(int[] cell : path){
                //iterate through MyCell[][] grid and find the cell with the same row and col as the cell in path
                for(int i = 0; i < grid.length; i++){
                    for(int j = 0; j < grid.length; j++){
                        if(grid[i][j].getRow() == cell[0] && grid[i][j].getCol() == cell[1]){
                            grid[i][j].getVbox().setStyle("-fx-background-color: #00ff00");
                        }
                    }
                }
            }
        });

        GridPane root = new GridPane();
        root.add(gridPane, 0,0);
        root.add(vbox, 1,0);

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
