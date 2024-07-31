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
import lk.acpt.phonestore2.dto.PhoneDTO;
import lk.acpt.phonestore2.model.PhoneModel;

import java.io.IOException;
import java.sql.SQLException;

public class PhoneUpdateController {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuantity;


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
    void search(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());

        PhoneDTO phoneDTO = null;
        try {
            phoneDTO = PhoneModel.search(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        txtBrand.setText(phoneDTO.getBrand());
        txtModel.setText(phoneDTO.getModel());
        txtPrice.setText(String.valueOf(phoneDTO.getPrice()));
        txtQuantity.setText(String.valueOf(phoneDTO.getQuantity()));
    }

    @FXML
    void update(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        String model = txtModel.getText();
        String brand = txtBrand.getText();
        Double price = Double.valueOf(txtPrice.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());

        int i = 0;
        try {
            i = PhoneModel.update(new PhoneDTO(id, model, brand, price, quantity));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (i>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setContentText("Update Successfully");
            alert.show();
            clear();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setContentText("Update Fail");
            alert.show();
            clear();
        }

    }
    private void clear(){
        txtQuantity.clear();
        txtPrice.clear();
        txtModel.clear();
        txtBrand.clear();
        txtPrice.clear();
        txtId.clear();
    }
}
