package lk.acpt.phonestore2.tm;

public class PhoneTM {
    private int id;
    private String model;
    private String brand;
    private double price;
    private int quantity;
    public PhoneTM(int id, String model, String brand, double price, int quantity) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
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
