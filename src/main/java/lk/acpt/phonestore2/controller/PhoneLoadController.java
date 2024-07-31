package lk.acpt.phonestore2.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.acpt.phonestore2.tm.PhoneTM;
import lk.acpt.phonestore2.dto.PhoneDTO;
import lk.acpt.phonestore2.model.PhoneModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PhoneLoadController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<PhoneTM> tblPhone;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tblPhone.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblPhone.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblPhone.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblPhone.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));
        tblPhone.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("quantity"));

        ArrayList<PhoneDTO>phoneDTOS;
        try {
            phoneDTOS = PhoneModel.load();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<PhoneTM> phoneTMS=new ArrayList<>();

        for (PhoneDTO phoneDTO:phoneDTOS){
            phoneTMS.add(new PhoneTM(
                    phoneDTO.getId(),
                    phoneDTO.getModel(),
                    phoneDTO.getBrand(),
                    phoneDTO.getPrice(),
                    phoneDTO.getQuantity()));
        }

        tblPhone.setItems(FXCollections.observableArrayList(phoneTMS));
    }
}
