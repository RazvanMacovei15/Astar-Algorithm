package razvan.astaralgorithm.View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import razvan.astaralgorithm.Domain.Algorithm;
import razvan.astaralgorithm.Domain.GridCreator;
import razvan.astaralgorithm.Domain.ListForSrcAndDest;
import razvan.astaralgorithm.Domain.MyCell;
import razvan.astaralgorithm.Service.AlgorithmService;

import java.util.*;

public class AlgorithmController {
    Queue<Map<MyCell[][], List<int[]>>> pathQueue = null;

    @FXML
    private Label warning;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button testButton;
    @FXML
    private Button startButton;
    @FXML
    private Label startLabel;
    @FXML
    private Label endLabel;
    @FXML
    private TextField rowsField;
    @FXML
    private TextField columnsField;
    @FXML
    private Button resetButton;
    private AlgorithmService algorithmService;
    private MyCell[][] grid;
    int[] src = {-1,-1};
    int[] dest = {-1,-1};
    private ListForSrcAndDest listForSrcAndDest;

    public void onStartButton() {
        listForSrcAndDest = new ListForSrcAndDest();

        startButton.setVisible(false);
        startLabel.setVisible(true);
        testButton.setVisible(true);
        resetButton.setVisible(true);
        rowsField.setVisible(true);
        columnsField.setVisible(true);

        stats();
    }

    public void onResetButton(){
        startLabel.setText("SELECT SOURCE");
        endLabel.setText("SELECT DESTINATION");
        startButton.setVisible(true);
        startLabel.setVisible(false);
        endLabel.setVisible(false);
        testButton.setVisible(false);
        warning.setVisible(false);

        algorithmService = this.getAlgorithmService();
        gridPane = algorithmService.getGridPane();

        gridPane.getChildren().clear();

        int rows = Integer.parseInt(rowsField.getText());
        int columns = Integer.parseInt(columnsField.getText());

        GridCreator gridCreator = new GridCreator(rows, columns);
        MyCell[][] grid = gridCreator.createGrid();
        GridPane pane = gridCreator.getGridPane();

        algorithmService.setGrid(grid);

        pane.setGridLinesVisible(true);

        gridCreator.printGrid(grid);
        gridPane.maxHeight(1000);
        gridPane.maxWidth(1000);

        gridPane.add(pane, 0, 0);
    }

    public void stats(){
        grid = algorithmService.getGrid();

        pathQueue = new LinkedList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                MyCell cell = grid[i][j];
                cell.getVbox().setOnMouseClicked(e -> {
                    if(!listForSrcAndDest.isFull()){
                        if(listForSrcAndDest.getElemAtIndex(0)[0] == -1 && listForSrcAndDest.getElemAtIndex(0)[1] == -1){
                            int[] coordinates = {cell.getRow(), cell.getCol()};
                            startLabel.setText("SOURCE: " + coordinates[0] + " " + coordinates[1]);
                            endLabel.setVisible(true);
                            listForSrcAndDest.add(coordinates);
                        } else if (listForSrcAndDest.getElemAtIndex(1)[0] == -1 && listForSrcAndDest.getElemAtIndex(1)[1] == -1){
                            startLabel.setText("SOURCE: " + listForSrcAndDest.getElemAtIndex(0)[0] + " " + listForSrcAndDest.getElemAtIndex(0)[1]);
                            endLabel.setText("DESTINATION: " + cell.getRow() + " " + cell.getCol());
                            int[] coordinates = {cell.getRow(), cell.getCol()};
                            listForSrcAndDest.add(coordinates);
                        }
                    }else{
                        endLabel.setText("SELECT DESTINATION");
                        startLabel.setText("SOURCE: " + cell.getRow() + " " + cell.getCol());
                        int[] coordinates = {cell.getRow(), cell.getCol()};
                        listForSrcAndDest.add(coordinates);
                    }
                });
            }
        }
    }

    public void onTestButton() {

        Algorithm algorithm = new Algorithm(algorithmService.getGrid());

        src = listForSrcAndDest.getElemAtIndex(0);
        dest = listForSrcAndDest.getElemAtIndex(1);

        if(listForSrcAndDest.getElemAtIndex(0)[0] == -1 && listForSrcAndDest.getElemAtIndex(0)[1] == -1){
            warning.setVisible(true);
            warning.setText("PLEASE SELECT A SOURCE!");
            return;
        }
        if(listForSrcAndDest.getElemAtIndex(1)[0] == -1 && listForSrcAndDest.getElemAtIndex(1)[1] == -1){
            warning.setVisible(true);
            warning.setText("PLEASE SELECT A DESTINATION!");
            return;
        }
        new Thread(()->{
            System.out.println("working on thread " + Thread.currentThread().getName());
            algorithm.aStarSearch(src, dest);
        }).start();
    }



    public void drawPath(List<int[]> path, MyCell[][] grid) {
        new Thread(() -> {
            for (int[] cell : path) {
                //iterate through MyCell[][] grid and find the cell with the same row and col as the cell in path
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        if (grid[i][j].getRow() == cell[0] && grid[i][j].getCol() == cell[1]) {
                            grid[i][j].getVbox().setStyle("-fx-background-color: #00ff00");
                        }
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void setService(AlgorithmService service) {
        this.algorithmService = service;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public AlgorithmService getAlgorithmService() {
        return algorithmService;
    }
}
