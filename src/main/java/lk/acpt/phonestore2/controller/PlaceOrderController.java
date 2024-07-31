package lk.acpt.phonestore2.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.acpt.phonestore2.db.DBConnection;
import lk.acpt.phonestore2.dto.OrderDetailDTO;
import lk.acpt.phonestore2.dto.PhoneDTO;
import lk.acpt.phonestore2.model.PhoneModel;
import lk.acpt.phonestore2.tm.OrderDetailTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable {
    @FXML
    private TableView<OrderDetailTM> tblPlaceOrder;

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
    private TextField txtQuantityOnHand;

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblTotal;

    @FXML
    private TextField txtOrderId;

    private ArrayList<OrderDetailTM> orderDetailTMS;

    private double subTotal = 0.0;


    @FXML
    void addToCart(ActionEvent event) {
        double total = Double.parseDouble(txtPrice.getText())*Integer.parseInt(txtQuantity.getText());

        OrderDetailTM orderDetailTM = new OrderDetailTM(
                Integer.parseInt(txtId.getText()),
                txtModel.getText(),
                txtBrand.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQuantity.getText()),
                total);
        orderDetailTMS.add(orderDetailTM);

        tblPlaceOrder.setItems(FXCollections.observableList(orderDetailTMS));
        subTotal+=total;
        lblTotal.setText(String.valueOf(subTotal));
    }

    @FXML
    void placeOrder(ActionEvent event) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String orderDate = dateFormat.format(date);

        ArrayList<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();

        for(OrderDetailTM orderDetailTM:orderDetailTMS){
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(
                    orderDetailTM.getId(),
                    orderDetailTM.getModel(),
                    orderDetailTM.getBrand(),
                    orderDetailTM.getPrice(),
                    orderDetailTM.getQuantity(),
                    orderDetailTM.getTotal());

            orderDetailDTOS.add(orderDetailDTO);
        }
        try {
            PhoneModel.makeOrder(txtOrderId.getText(),subTotal,orderDate,orderDetailDTOS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        txtQuantityOnHand.setText(String.valueOf(phoneDTO.getQuantity()));
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
//        DBConnection dbConnection = new DBConnection();
//        DBConnection.getDbConnection();

//        try {
//            DBConnection.getDbConnection().getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblPlaceOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblPlaceOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblPlaceOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblPlaceOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));
        tblPlaceOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblPlaceOrder.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));


        orderDetailTMS= new ArrayList<>();

    }
}
