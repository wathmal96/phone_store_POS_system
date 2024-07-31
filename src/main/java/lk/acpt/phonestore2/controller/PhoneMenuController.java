package lk.acpt.phonestore2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PhoneMenuController {

    @FXML
    private AnchorPane root;

    @FXML
    void manage(ActionEvent event) throws IOException {
        //Catch the stage
        Stage stage = (Stage) this.root.getScene().getWindow();
        //Load next fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/acpt/phonestore2/phone-manage-view.fxml"));

        //create a scene
        Scene scene = new Scene(fxmlLoader.load());

        //set for the stage
        stage.setScene(scene);
    }

    @FXML
    void order(ActionEvent event) throws IOException {
        //Catch the stage
        Stage stage = (Stage) this.root.getScene().getWindow();
        //Load next fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/acpt/phonestore2/place-order-view.fxml"));

        //create a scene
        Scene scene = new Scene(fxmlLoader.load());

        //set for the stage
        stage.setScene(scene);
    }
}
