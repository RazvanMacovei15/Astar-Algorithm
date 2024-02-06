package razvan.astaralgorithm.View;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import razvan.astaralgorithm.Domain.GridCreator;
import razvan.astaralgorithm.Domain.MyCell;
import razvan.astaralgorithm.Service.AlgorithmService;

import java.util.List;

public class AlgorithmController {
    @FXML
    private GridPane gridPane;

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    private AlgorithmService algorithmService;

    public void drawPath(List<int[]> path, MyCell[][] grid){
        new Thread(()->{
            for(int[] cell : path){
                //iterate through MyCell[][] grid and find the cell with the same row and col as the cell in path
                for(int i = 0; i < grid.length; i++){
                    for(int j = 0; j < grid.length; j++){
                        if(grid[i][j].getRow() == cell[0] && grid[i][j].getCol() == cell[1]){
                            grid[i][j].getVbox().setStyle("-fx-background-color: #00ff00");
                        }
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void drawGrid(GridPane gridPane){
        algorithmService.drawGrid(gridPane);
    }

    public void setService(AlgorithmService service) {
        this.algorithmService = service;
    }

    public AlgorithmService getAlgorithmService() {
        return algorithmService;
    }
}
