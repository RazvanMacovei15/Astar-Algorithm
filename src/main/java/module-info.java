module razvan.astaralgorithm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens razvan.astaralgorithm to javafx.fxml;
    exports razvan.astaralgorithm;
    opens razvan.astaralgorithm.Domain to javafx.graphics, javafx.fxml;
    exports razvan.astaralgorithm.Domain;
    opens razvan.astaralgorithm.View to javafx.graphics, javafx.fxml;
    exports razvan.astaralgorithm.View;
    opens razvan.astaralgorithm.Service to javafx.graphics, javafx.fxml;
    exports razvan.astaralgorithm.Service;
    exports razvan.astaralgorithm.HelperClasses;
    opens razvan.astaralgorithm.HelperClasses to javafx.fxml, javafx.graphics;
}