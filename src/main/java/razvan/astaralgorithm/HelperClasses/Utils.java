package razvan.astaralgorithm.HelperClasses;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public class Utils {
    public void changeColors(List<Circle> circles){
        new Thread(() -> {
            for(Circle circle : circles){
                Platform.runLater(()-> {
                    circle.setFill(Color.GREY);
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
