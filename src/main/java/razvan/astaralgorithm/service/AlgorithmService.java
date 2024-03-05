package razvan.astaralgorithm.service;

import javafx.scene.layout.GridPane;
import razvan.astaralgorithm.domain.Algorithm;
import razvan.astaralgorithm.domain.MyCell;

public class AlgorithmService {
    private Algorithm algorithm;
    private MyCell[][] grid;
    private GridPane gridPane;

    public AlgorithmService(MyCell[][] grid) {
        this.grid = grid;
    }

    public MyCell[][] getGrid() {
        return grid;
    }

    public void setGrid(MyCell[][] grid) {
        this.grid = grid;
    }


    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public void drawGrid(GridPane gridPane) {

    }
}
