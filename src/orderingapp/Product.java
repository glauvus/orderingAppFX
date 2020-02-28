package orderingapp;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private double total;
    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity=1;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getTotal() {
        return price*quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
