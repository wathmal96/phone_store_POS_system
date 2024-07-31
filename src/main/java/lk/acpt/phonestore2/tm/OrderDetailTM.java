package lk.acpt.phonestore2.tm;

public class OrderDetailTM {
    private int id;
    private String model;
    private String brand;
    private double price;
    private int quantity;


    private  double total;
    public OrderDetailTM(int id, String model, String brand, double price, int quantity,double total) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }
    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
