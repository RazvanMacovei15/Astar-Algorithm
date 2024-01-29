package razvan.astaralgorithm.HelperClasses;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import razvan.astaralgorithm.Domain.MyCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Creator {
    public static Pane createGridPane(int GRID_SIZE){
        Pane gridPane = new Pane();
        List<MyCell> cells = new ArrayList<>();

        double cellWidth = 1000.0 / GRID_SIZE;
        double cellHeight = 1000.0 / GRID_SIZE;

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                MyCell newCell = new MyCell(new VBox(), row+1, col+1);
                VBox vbox = newCell.getVbox();
                vbox.setPrefSize(cellWidth, cellHeight);
                vbox.setLayoutX(col * cellWidth);
                vbox.setLayoutY(row * cellHeight);

                cells.add(newCell);
                gridPane.getChildren().add(vbox);
            }
        }
        randomiseBlocks(cells);
        return gridPane;
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
                vbox.setStyle("-fx-background-color: " + toHex(Color.BLACK) + ";");
            }
            else{
                vbox.setStyle("-fx-background-color: " + toHex(Color.WHITE) + ";");
            }
        }
    }

    private static String toHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
