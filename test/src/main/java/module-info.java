module com.demofx {
    requires java.sql;

    //opens com.demofx.model to javafx.base;

    opens com.demofx to javafx.fxml;
    exports com.demofx;
}