module lk.acpt.phonestore2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires  java.sql;


    opens lk.acpt.phonestore2 to javafx.fxml;
    exports lk.acpt.phonestore2;

    exports lk.acpt.phonestore2.controller;
    opens lk.acpt.phonestore2.controller to javafx.fxml;

    exports lk.acpt.phonestore2.tm;
    opens lk.acpt.phonestore2.tm to javafx.fxml;
}