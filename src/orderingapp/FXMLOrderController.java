/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderingapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author homepc
 */
public class FXMLOrderController implements Initializable {

    @FXML private TableView<Product> orderViewTable;
    @FXML private TableColumn<Product, String> nameCol;
    @FXML private TableColumn<Product, Number> priceCol;
    @FXML private TableColumn<Product, Number> quantityCol;
    @FXML private TableColumn<Product, Number> totalCol;
    @FXML private Label nameLabel, addressLabel, commentsLabel, totalLabel;
    @FXML private Button finishButton;
    
    int selectedOrderIndex;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Product, Number>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Product, Number>("quantity"));
        totalCol.setCellValueFactory(new PropertyValueFactory<Product, Number>("total"));
    }
    
    public void setProductList(Order order) {
        orderViewTable.setItems(FXCollections.observableArrayList(order.getProducts()));
        nameLabel.setText(order.getClientName());
        addressLabel.setText(order.getClientAddress());
        commentsLabel.setText(order.getComments());
        totalLabel.setText(String.valueOf(order.getTotalPrice()));
    }
    
    public void selectedOrderPosition(int x) {
        selectedOrderIndex=x;
    }
    
    @FXML
    private void finishOrder(MouseEvent event) {
        Order selectedOrder = FXMLDocumentController.getOrders().get(selectedOrderIndex);
        FXMLDocumentController.getOrders().remove(selectedOrder);
        finishButton.setDisable(true);
    }
}
