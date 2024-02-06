package razvan.astaralgorithm.View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import razvan.astaralgorithm.APP;
import razvan.astaralgorithm.Domain.Algorithm;
import razvan.astaralgorithm.Domain.GridCreator;
import razvan.astaralgorithm.Domain.MyCell;
import razvan.astaralgorithm.Service.AlgorithmService;

import java.io.IOException;

public class ParametersController {
    @FXML
    TextField rowsTextField;
    @FXML
    TextField columnsTextField;
    @FXML
    Button startButton;

    private AlgorithmController algorithmController;

    public void setAlgorithmController(AlgorithmController algorithmController) {
        this.algorithmController = algorithmController;
    }

    public void startButtonAction() {
        int rows = Integer.parseInt(rowsTextField.getText());
        int columns = Integer.parseInt(columnsTextField.getText());
        GridCreator gridCreator = new GridCreator(rows, columns);
        MyCell[][] grid = gridCreator.getMyCellGrid();
        gridCreator.printGrid(grid);
        Algorithm algorithm = new Algorithm(grid);
        AlgorithmService algorithmService = new AlgorithmService(algorithm);

        loadAlgorithmScene(algorithmService);
    }

    private void loadAlgorithmScene(AlgorithmService service) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/razvan/astaralgorithm/MainScreen.fxml"));
            Parent root = loader.load();
            Stage primaryStage = APP.getPrimaryStage();

            AlgorithmController algorithmController = loader.getController();
            algorithmController.setService(service);

            Scene algorithmScene = new Scene(root, 1200, 1000);
            primaryStage.setScene(algorithmScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
