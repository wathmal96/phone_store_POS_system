package lk.acpt.phonestore2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.acpt.phonestore2.model.PhoneModel;

import java.io.IOException;
import java.sql.SQLException;

public class PhoneDeleteController {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtId;

    @FXML
    void back(MouseEvent event) throws IOException {
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
    void cancel(ActionEvent event) {
        System.exit(0);

    }

    @FXML
    void delete(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());

        int i = 0;
        try {
            i = PhoneModel.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (i>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setContentText("DELETE SUCCESSFULLY");
            alert.show();
            clear();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setContentText("FAIL to delete phone");
            alert.show();
            clear();
        }
    }
    private void clear(){
        txtId.clear();
    }
}
