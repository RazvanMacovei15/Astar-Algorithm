package razvan.astaralgorithm.Domain;

import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GridCreator {
    private int ROWS;
    private int COLS;
    private GridPane gridPane;

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public GridCreator(int ROW, int COL, GridPane gridPane) {
        this.ROWS = ROW;
        this.COLS = COL;
        this.gridPane = gridPane;
    }

    public GridCreator(int ROW, int COL) {
        this.ROWS = ROW;
        this.COLS = COL;
    }

    public int getROWS() {
        return ROWS;
    }

    public void setROWS(int ROWS) {
        this.ROWS = ROWS;
    }

    public int getCOLS() {
        return COLS;
    }

    public void setCOLS(int COLS) {
        this.COLS = COLS;
    }


    public MyCell[][] createGrid() {
        GridPane gridPane = new GridPane();

        double cellWidth = 1000.0 / ROWS;
        double cellHeight = 1000.0 / COLS;

        MyCell[][] myCellGrid = new MyCell[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                myCellGrid[row][col] = new MyCell(row, col);

                VBox vbox =  myCellGrid[row][col].getVbox();
                vbox.setPrefSize(cellWidth, cellHeight);
                vbox.setLayoutX(col * cellWidth);
                vbox.setLayoutY(row * cellHeight);

            }
        }

        setGridPane(gridPane);

        return myCellGrid;
    }

    public void printGrid(MyCell[][] grid){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                System.out.print(grid[i][j].getRow() + " " + grid[i][j].getCol() + "->" + grid[i][j].isObstacle() + " | ");
            }
            System.out.println();
        }
    }
}
