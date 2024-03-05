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
    opens razvan.astaralgorithm.domain to javafx.graphics, javafx.fxml;
    exports razvan.astaralgorithm.domain;
    opens razvan.astaralgorithm.view to javafx.graphics, javafx.fxml;
    exports razvan.astaralgorithm.view;
    opens razvan.astaralgorithm.service to javafx.graphics, javafx.fxml;
    exports razvan.astaralgorithm.service;
    exports razvan.astaralgorithm.helper;
    opens razvan.astaralgorithm.helper to javafx.fxml, javafx.graphics;
}