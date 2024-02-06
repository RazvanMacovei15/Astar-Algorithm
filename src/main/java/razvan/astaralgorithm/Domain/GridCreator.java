package razvan.astaralgorithm.Domain;

import javafx.scene.layout.VBox;

public class GridCreator {
    private int ROW;
    private int COL;
    private MyCell[][] myCellGrid;

    public GridCreator(int ROW, int COL) {
        this.ROW = ROW;
        this.COL = COL;
        this.myCellGrid = createGrid(ROW, COL);
    }

    public int getROW() {
        return ROW;
    }

    public void setROW(int ROW) {
        this.ROW = ROW;
    }

    public int getCOL() {
        return COL;
    }

    public void setCOL(int COL) {
        this.COL = COL;
    }

    public MyCell[][] getMyCellGrid() {
        return myCellGrid;
    }

    public void setMyCellGrid(MyCell[][] myCellGrid) {
        this.myCellGrid = myCellGrid;
    }

    private MyCell[][] createGrid(int ROW, int COL) {
        MyCell[][] myCellGrid = new MyCell[ROW][COL];
        for (int row = 0; row < ROW ; row++) {
            for (int col = 0 ; col < COL ; col++) {
                myCellGrid[row][col] = new MyCell(row, col); // Create a cell object for each cell
            }
        }
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
