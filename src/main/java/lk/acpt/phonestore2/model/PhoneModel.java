package lk.acpt.phonestore2.model;

import lk.acpt.phonestore2.db.DBConnection;
import lk.acpt.phonestore2.dto.OrderDetailDTO;
import lk.acpt.phonestore2.dto.PhoneDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhoneModel {
    public static int add(PhoneDTO phoneDto) throws SQLException, ClassNotFoundException {


        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("insert into phone2  (brand,model,price,quantity)values(?,?,?,?)");

        stm.setObject(1, phoneDto.getModel());
        stm.setObject(2, phoneDto.getBrand());
        stm.setObject(3, phoneDto.getPrice());
        stm.setObject(4, phoneDto.getQuantity());

        int i = stm.executeUpdate();
        return i;


    }

    public static int delete(int id) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDbConnection().getConnection();

        PreparedStatement stm = connection.prepareStatement("delete from  phone2 where id=?");

        stm.setObject(1, id);

        int i = stm.executeUpdate();

        return i;


    }

    public static ArrayList<PhoneDTO> load() throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDbConnection().getConnection();

        PreparedStatement stm = connection.prepareStatement("select  * from phone2");

        ResultSet resultSet = stm.executeQuery();

        ArrayList<PhoneDTO> phoneDTOS = new ArrayList<>();

        while (resultSet.next()) {
            phoneDTOS.add(new PhoneDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getInt(5)));
        }
        return phoneDTOS;


    }

    public static PhoneDTO search(int id) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDbConnection().getConnection();

        PreparedStatement stm = connection.prepareStatement("select * from phone2 where id=?");

        stm.setObject(1, id);

        ResultSet resultSet = stm.executeQuery();
        PhoneDTO phoneDTO = null;
        while (resultSet.next()) {
            phoneDTO = new PhoneDTO(
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5));
        }

        return phoneDTO;
    }

    public static int update(PhoneDTO phoneDTO) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDbConnection().getConnection();

        PreparedStatement stm = connection.prepareStatement("update  phone2 set model=?,brand=?,price=?,quantity=? where id = ?");


        stm.setObject(1, phoneDTO.getModel());
        stm.setObject(2, phoneDTO.getBrand());
        stm.setObject(3, phoneDTO.getPrice());
        stm.setObject(4, phoneDTO.getQuantity());
        stm.setObject(5, phoneDTO.getId());

        int i = stm.executeUpdate();

        return i;


    }

    public static boolean makeOrder(
            String orderId,
            double subTotal,
            String orderDate,
            ArrayList<OrderDetailDTO> orderDetailDTOS) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);

        PreparedStatement stm1 = connection.prepareStatement("insert into orders  (oid,order_date,amount)values(?,?,?)");

        stm1.setObject(1, orderId );
        stm1.setObject(2, orderDate);
        stm1.setObject(3, subTotal);

        int i = stm1.executeUpdate();
        System.out.println(i);

        if (i > 0) {
            for (OrderDetailDTO dto : orderDetailDTOS) {
                PreparedStatement stm2 = connection.prepareStatement(
                        "insert into order_detail (oid,pid,qty,amount)values(?,?,?,?)");

                stm2.setObject(1,orderId );
                stm2.setObject(2, dto.getId());
                stm2.setObject(3, dto.getQuantity());
                stm2.setObject(4, dto.getTotal());

                int j = stm2.executeUpdate();

                if (j > 0) {
                    //update item table
                } else {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

            }

            //persist all the data to database
            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } else {
            //remove cached data
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
    }
}
