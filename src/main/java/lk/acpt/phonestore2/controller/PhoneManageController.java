package lk.acpt.phonestore2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class PhoneManageController {
    @FXML
    private AnchorPane root;

    @FXML
    void add(ActionEvent event) throws IOException {
        //Catch the stage
        Stage stage = (Stage) this.root.getScene().getWindow();
        //Load next fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/acpt/phonestore2/phone-add-view.fxml"));

        //create a scene
        Scene scene = new Scene(fxmlLoader.load());

        //set for the stage
        stage.setScene(scene);
    }

    @FXML
    void delete(ActionEvent event) throws IOException {
        //Catch the stage
        Stage stage = (Stage) this.root.getScene().getWindow();
        //Load next fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/acpt/phonestore2/phone-delete-view.fxml"));

        //create a scene
        Scene scene = new Scene(fxmlLoader.load());

        //set for the stage
        stage.setScene(scene);
    }

    @FXML
    void upadate(ActionEvent event) throws IOException {
        //Catch the stage
        Stage stage = (Stage) this.root.getScene().getWindow();
        //Load next fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/acpt/phonestore2/phone-update-view.fxml"));

        //create a scene
        Scene scene = new Scene(fxmlLoader.load());

        //set for the stage
        stage.setScene(scene);
    }

    @FXML
    void viewAll(ActionEvent event) throws IOException {
        //Catch the stage
        Stage stage = (Stage) this.root.getScene().getWindow();
        //Load next fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/acpt/phonestore2/phone-load-view.fxml"));

        //create a scene
        Scene scene = new Scene(fxmlLoader.load());

        //set for the stage
        stage.setScene(scene);
    }

    @FXML
    void back(MouseEvent event) throws IOException {
//Catch the stage
        Stage stage = (Stage) this.root.getScene().getWindow();
        //Load next fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/acpt/phonestore2/phone-menu-view.fxml"));

        //create a scene
        Scene scene = new Scene(fxmlLoader.load());

        //set for the stage
        stage.setScene(scene);
    }

}
