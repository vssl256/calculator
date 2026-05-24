module org.example.calculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires atlantafx.base;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;

    opens org.example.calculator to javafx.fxml;
    exports org.example.calculator;
}