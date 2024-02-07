package razvan.astaralgorithm.View;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import razvan.astaralgorithm.APP;
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

    public AlgorithmController getAlgorithmController() {
        return algorithmController;
    }

    public void setAlgorithmController(AlgorithmController algorithmController) {
        this.algorithmController = algorithmController;
    }

    public void startButtonAction() {
        int rows = Integer.parseInt(rowsTextField.getText());
        int columns = Integer.parseInt(columnsTextField.getText());

        GridCreator gridCreator = new GridCreator(rows, columns);
        MyCell[][] grid = gridCreator.createGrid();
        GridPane pane = gridCreator.getGridPane();

        AlgorithmService algorithmService = new AlgorithmService(grid);
        algorithmService.setGridPane(pane);

        loadGridScene(algorithmService);
    }

    private void loadGridScene(AlgorithmService service) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/razvan/astaralgorithm/MainScreen.fxml"));
            Parent root = loader.load();
            Stage primaryStage = APP.getPrimaryStage();

            AlgorithmController algorithmController = loader.getController();
            GridPane gridPane = service.getGridPane();

            GridPane gp = algorithmController.getGridPane();
            gp.maxHeight(1000);
            gp.maxWidth(1000);
            gridPane.gridLinesVisibleProperty().setValue(true);
            gp.add(gridPane, 0, 0);

            algorithmController.setService(service);

            Scene algorithmScene = new Scene(root, 1210, 1000);
            primaryStage.setScene(algorithmScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
