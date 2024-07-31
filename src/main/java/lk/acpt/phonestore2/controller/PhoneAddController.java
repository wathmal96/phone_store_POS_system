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

public class PhoneAddController {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuantity;

    @FXML
    void add(ActionEvent event) {
        String model = txtModel.getText();
        String brand = txtBrand.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());

        int i = 0;
        try {
            i = PhoneModel.add(new PhoneDTO(model, brand, price, quantity));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(i>0){
            clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setContentText("Phone add SUCCESSFULLY");
            alert.show();
            clear();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setContentText("FAIL to add phone");
            alert.show();
            clear();
        }
    }

    private void clear(){
        txtBrand.clear();
        txtModel.clear();
        txtPrice.clear();
        txtQuantity.clear();
    }

    @FXML

    void cancel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void back(MouseEvent event) throws IOException {
        System.out.println("Back clicked...!");
        //Catch the stage
        Stage stage = (Stage) this.root.getScene().getWindow();
        System.out.println(stage);
        //Load next fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/acpt/phonestore2/phone-manage-view.fxml"));
        System.out.println(fxmlLoader);
        //create a scene
        Scene scene = new Scene(fxmlLoader.load());

        //set for the stage
        stage.setScene(scene);
    }
}
