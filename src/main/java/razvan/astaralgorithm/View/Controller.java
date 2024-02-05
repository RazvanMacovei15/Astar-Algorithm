package razvan.astaralgorithm.View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import razvan.astaralgorithm.HelperClasses.Utils;
import razvan.astaralgorithm.Service.AlgorithmService;

import java.util.List;

public class Controller {
    private AlgorithmService algorithmService;

    @FXML HBox hBox;
    @FXML
    Button button;
    private List<Circle> circles;
    private Utils utils;
    public void initialize() {
        circles = createCircles();
        utils = new Utils();

        // Create an HBox and add the circles to it
        hBox.setSpacing(20); // Set the spacing between circles
        hBox.getChildren().addAll(circles);
    }

    // Helper method to create a circle with specified radius and color
    private Circle createCircle(double radius, Color color) {
        Circle circle = new Circle(radius);
        circle.setFill(color);
        return circle;
    }

    private List<Circle> createCircles(){
        // Create circles with different colors
        Circle circle1 = createCircle(30, Color.RED);
        Circle circle2 = createCircle(30, Color.GREEN);
        Circle circle3 = createCircle(30, Color.BLUE);
        Circle circle4 = createCircle(30, Color.ORANGE);

        return List.of(circle1, circle2, circle3, circle4);
    }

    public void onButtonClicked(){
        utils.changeColors(circles);
    }
}
