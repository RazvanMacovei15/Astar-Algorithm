package razvan.astaralgorithm.HelperClasses;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import razvan.astaralgorithm.Domain.MyCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Creator {
    static int row;
    static int col;
    static MyCell[][] grid = null;
    static int[][] intGrid = null;

    public static MyCell[][] getGrid() {
        return grid;
    }

    public static int[][] getIntGrid() {
        return intGrid;
    }

    public static Pane createGridPane(int ROWS, int COLS){
        grid = new MyCell[ROWS][COLS];

        Pane gridPane = new Pane();
        List<MyCell> cells = new ArrayList<>();

        double cellWidth = 1000.0 / ROWS;
        double cellHeight = 1000.0 / COLS;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                MyCell newCell = new MyCell(row, col);
                VBox vbox = newCell.getVbox();
                vbox.setPrefSize(cellWidth, cellHeight);
                vbox.setLayoutX(col * cellWidth);
                vbox.setLayoutY(row * cellHeight);

                cells.add(newCell);
                grid[row][col] = newCell;

                gridPane.getChildren().add(vbox);
            }
        }
        randomiseBlocks(cells);
        printGrid();
        intGrid = transformGRIDToIntGrid(grid);
        System.out.println();
        printIntGrid(intGrid);
        return gridPane;
    }

    private static int[][] transformGRIDToIntGrid(MyCell[][] grid){
        int[][] intGrid = new int[grid.length][grid.length];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                intGrid[i][j] = grid[i][j].isObstacle() ? 1 : 0;
            }
        }
        return intGrid;
    }

    public static void printIntGrid(int[][] intGrid){
        for(int i = 0; i < intGrid.length; i++){
            for(int j = 0; j < intGrid.length; j++){
                System.out.print(intGrid[i][j] + ", ");
            }
            System.out.println();
        }
    }

    public static void randomiseBlocks(List<MyCell> cells){
        Random random = new Random();
        for(MyCell cell : cells){
            boolean value = random.nextBoolean();
            cell.setObstacle(value);
        }
        for(MyCell cell : cells){
            VBox vbox = cell.getVbox();
            vbox.setOnMouseClicked(e -> {
                System.out.println("Clicked on cell: " + cell.getRow() + " " + cell.getCol());
            });
            if(cell.isObstacle()){
                vbox.setStyle("-fx-background-color: " + toHex(Color.DARKGREEN) + ";");
            }
            else{
                vbox.setStyle("-fx-background-color: " + toHex(Color.LIGHTBLUE) + ";");
            }
        }
    }

    public static String toHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    private static void printGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                int value = grid[i][j].isObstacle() ? 1 : 0;
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
