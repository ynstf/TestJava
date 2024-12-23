module com.demofx {
    requires java.sql;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    opens com.demofx.model to javafx.base;

    opens com.demofx to javafx.fxml;
    exports com.demofx;
}