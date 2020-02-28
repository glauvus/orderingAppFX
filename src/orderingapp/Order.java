package orderingapp;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> products;
    private String clientName;
    private String clientAddress;
    private String comments;
    private double totalPrice;
    
    public Order() {
        products = new ArrayList<>();
    }

   
    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    public String getClientAddress() {
        return clientAddress;
    }
    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
