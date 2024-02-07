package razvan.astaralgorithm.HelperClasses;

import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import razvan.astaralgorithm.Domain.MyCell;

public class ColorChanger {
    public static void highlightStartCell(int p, int q, MyCell[][] myCellGrid) {
        Platform.runLater(() -> {
            // Set the background color
            BackgroundFill backgroundFill = new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
            Background newBackground = new Background(backgroundFill);
            myCellGrid[p][q].getVbox().setBackground(newBackground);
        });
    }
    public static void highlightEndCell(int p, int q, MyCell[][] myCellGrid) {
        Platform.runLater(() -> {
            // Set the background color
            BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
            Background newBackground = new Background(backgroundFill);
            myCellGrid[p][q].getVbox().setBackground(newBackground);
        });
    }
    public static void backToGreen(int p, int q, MyCell[][] myCellGrid) {
        Platform.runLater(() -> {
            // Set the background color
            BackgroundFill backgroundFill = new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
            Background newBackground = new Background(backgroundFill);
            myCellGrid[p][q].getVbox().setBackground(newBackground);
            myCellGrid[p][q].getLabel().setText("");
        });
    }

    public static String toHex(Color color) {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);

        // Convert each component to hexadecimal and concatenate them
        String hex = String.format("#%02X%02X%02X", red, green, blue);

        return hex;
    }

}
