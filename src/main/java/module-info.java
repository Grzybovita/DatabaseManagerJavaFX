module pokermanagerapp {
    exports pokermanagerapp;
    exports controller;

    opens controller to javafx.fxml;
    opens model to javafx.base;

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    //requires rt;
}