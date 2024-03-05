package razvan.astaralgorithm.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import razvan.astaralgorithm.domain.Algorithm;
import razvan.astaralgorithm.domain.GridCreator;
import razvan.astaralgorithm.domain.ListForSrcAndDest;
import razvan.astaralgorithm.domain.MyCell;
import razvan.astaralgorithm.helper.ColorChanger;
import razvan.astaralgorithm.service.AlgorithmService;

import java.util.*;

public class AlgorithmController {
    private int count = 1;
    private Set<MyCell> visitedCells;
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
    int[] src = {-1, -1};
    int[] dest = {-1, -1};
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

    public void onResetButton() {
        startLabel.setText("SELECT SOURCE");
        endLabel.setText("SELECT DESTINATION");
        startButton.setVisible(true);
        startLabel.setVisible(false);
        endLabel.setVisible(false);
        testButton.setVisible(false);
        warning.setVisible(false);
        count = 1;

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

        gridPane.maxHeight(1000);
        gridPane.maxWidth(1000);

        gridPane.add(pane, 0, 0);
    }

    public void stats() {
        grid = algorithmService.getGrid();
        visitedCells = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                MyCell cell = grid[i][j];
                cell.getVbox().setOnMouseClicked(e -> {

                    if (!cell.isObstacle()) {
                        return;
                    }
                    if (!listForSrcAndDest.isFull()) {
                        if (listForSrcAndDest.getElemAtIndex(0)[0] == -1 && listForSrcAndDest.getElemAtIndex(0)[1] == -1) {
                            int[] coordinates = {cell.getRow(), cell.getCol()};
                            ColorChanger.highlightStartCell(cell.getRow(), cell.getCol(), grid);
                            cell.getLabel().setText(String.valueOf(count));
                            startLabel.setText("SOURCE: " + coordinates[0] + " " + coordinates[1]);
                            endLabel.setVisible(true);
                            listForSrcAndDest.add(coordinates);
                        } else if (listForSrcAndDest.getElemAtIndex(1)[0] == -1 && listForSrcAndDest.getElemAtIndex(1)[1] == -1) {
                            startLabel.setText("SOURCE: " + listForSrcAndDest.getElemAtIndex(0)[0] + " " + listForSrcAndDest.getElemAtIndex(0)[1]);
                            endLabel.setText("DESTINATION: " + cell.getRow() + " " + cell.getCol());
                            int[] coordinates = {cell.getRow(), cell.getCol()};
                            ColorChanger.highlightEndCell(cell.getRow(), cell.getCol(), grid);
                            cell.getLabel().setText(String.valueOf(count));
                            listForSrcAndDest.add(coordinates);
                        }
                    } else {
                        MyCell[] startCell = {grid[listForSrcAndDest.getElemAtIndex(0)[0]][listForSrcAndDest.getElemAtIndex(0)[1]]};
                        MyCell[] endCell = {grid[listForSrcAndDest.getElemAtIndex(1)[0]][listForSrcAndDest.getElemAtIndex(1)[1]]};

                        if (visitedCells.contains(startCell[0]) && visitedCells.contains(endCell[0])) {
                            endLabel.setText("SELECT DESTINATION");
                            startLabel.setText("SOURCE: " + cell.getRow() + " " + cell.getCol());
                            int[] coordinates = {cell.getRow(), cell.getCol()};
                            ColorChanger.highlightStartCell(cell.getRow(), cell.getCol(), grid);
                            cell.getLabel().setText(String.valueOf(count));
                            listForSrcAndDest.add(coordinates);
                        } else {
                            ColorChanger.backToGreen(listForSrcAndDest.getElemAtIndex(0)[0], listForSrcAndDest.getElemAtIndex(0)[1], grid);
                            ColorChanger.backToGreen(listForSrcAndDest.getElemAtIndex(1)[0], listForSrcAndDest.getElemAtIndex(1)[1], grid);
                            endLabel.setText("SELECT DESTINATION");
                            startLabel.setText("SOURCE: " + cell.getRow() + " " + cell.getCol());
                            int[] coordinates = {cell.getRow(), cell.getCol()};
                            ColorChanger.highlightStartCell(cell.getRow(), cell.getCol(), grid);
                            cell.getLabel().setText(String.valueOf(count));
                            listForSrcAndDest.add(coordinates);
                        }
                    }
                });
            }
        }
    }

    public void onFindPathButton() {

        int c1 = count;

        Algorithm algorithm = new Algorithm(algorithmService.getGrid());

        src = listForSrcAndDest.getElemAtIndex(0);
        dest = listForSrcAndDest.getElemAtIndex(1);

        if (listForSrcAndDest.getElemAtIndex(0)[0] == -1 && listForSrcAndDest.getElemAtIndex(0)[1] == -1) {
            warning.setVisible(true);
            warning.setText("PLEASE SELECT A SOURCE!");
            return;
        }
        if (listForSrcAndDest.getElemAtIndex(1)[0] == -1 && listForSrcAndDest.getElemAtIndex(1)[1] == -1) {
            warning.setVisible(true);
            warning.setText("PLEASE SELECT A DESTINATION!");
            return;
        }

        visitedCells.add(grid[src[0]][src[1]]);
        visitedCells.add(grid[dest[0]][dest[1]]);

        Label startLabel = grid[src[0]][src[1]].getLabel();
        startLabel.setText(String.valueOf(c1));
        Label endLabel = grid[dest[0]][dest[1]].getLabel();
        endLabel.setText(String.valueOf(c1));

        Thread thread = new Thread(() -> {
            algorithm.aStarSearch(src, dest, c1);
        });
        thread.start();
        count++;
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
