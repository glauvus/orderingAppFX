/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderingapp;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author homepc
 */
public class FXMLDocumentController implements Initializable {
    
    Product marouli, melitzanosalata, pantzari, roka, tzatziki, xtypiti, xoriatiki,
            kalamarakia, kaserokroketes, kolokythakia, patataFournou, piperiesFlorinis, xaloumiSxaras, xtapodi,
            kempap, kontosouvli, mpifteki, mpiftekiGemisto, mprizolaXoirini, gyrosXoirinos, gyrosKotopoulo, paidakia, souvlakiXoirino, souvlakiKotopoulo, filetoKotopoulo, psaronefri,
            arni, kotsi, laxanontolmades, mousakas, papoutsakia, soutzoukakia,
            garides, gauros, lauraki, mpakaliaros, mydia, sardela, tsipoura,
            galaktompoureko, kataifi, mpaklavas, xalvas,
            cocaCola, fanta, sprite, souroti,
            amstel, heineken, mythos, fix,
            kokkino, leuko, roze;
    
    private ObservableList<String> menuCategories = FXCollections.observableArrayList("Σαλάτες", "Ορεκτικά", "Της ώρας", "Μαγειρευτά", "Ψάρια", "Επιδόρπια", "Αναψυκτικά", "Μπύρες", "Κρασιά");
    
    private ObservableList<String> salates = FXCollections.observableArrayList("Μαρούλι", "Μελιτζανοσαλάτα", "Παντζάρι", "Ρόκα", "Τζατζίκι", "Χτυπητή", "Χωριάτικη");
    private ObservableList<String> orektika = FXCollections.observableArrayList("Καλαμαράκια", "Κασεροκροκέτες", "Κολοκυθάκια", "Πατάτα φούρνου", "Πιπεριές φλωρίνης", "Χαλούμι σχάρας", "Χταπόδι");
    private ObservableList<String> tisOras = FXCollections.observableArrayList("Κεμπάπ", "Κοντοσούβλι", "Μπιφτέκι", "Μπιφτέκι γεμιστό", "Μπριζόλα χοιρινή", "Γύρος χοιρινός", "Γύρος κοτόπουλο", "Παϊδάκια", "Σουβλάκι χοιρινό", "Σουβλάκι κοτόπουλο", "Φιλέτο κοτόπουλο", "Ψαρονέφρι");
    private ObservableList<String> mageireuta = FXCollections.observableArrayList("Αρνί", "Κότσι", "Λαχανοντολμάδες", "Μουσακάς", "Παπουτσάκια", "Σουτζουκάκια");
    private ObservableList<String> psaria = FXCollections.observableArrayList("Γαρίδες", "Γαύρος", "Λαυράκι", "Μπακαλιάρος", "Μύδια", "Σαρδέλα", "Τσιπούρα");
    private ObservableList<String> epidorpia = FXCollections.observableArrayList("Γαλακτομπούρεκο", "Καταϊφι", "Μπακλαβάς", "Χαλβάς");
    private ObservableList<String> anapsyktika = FXCollections.observableArrayList("Coca Cola", "Fanta", "Sprite", "Σουρωτή");
    private ObservableList<String> mpyres = FXCollections.observableArrayList("Amstel", "Heineken", "Mythos", "Fix");
    private ObservableList<String> krasia = FXCollections.observableArrayList("Κόκκινο", "Λευκό", "Ροζέ");
    
    private ObservableList<Product> products = FXCollections.observableArrayList();
    private static ObservableList<Order> orders = FXCollections.observableArrayList();
    
    private double sum=0.0;
    
    @FXML private ListView<String> menuList;
    @FXML private ListView<String> menuOptions;
    @FXML private TableView<Product> orderTable;
    @FXML private TableColumn<Product, String> nameCol;
    @FXML private TableColumn<Product, Number> priceCol;
    @FXML private TableColumn<Product, Number> quantityCol;
    @FXML private TableColumn<Product, Number> totalCol;
    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextArea commentsField;
    @FXML private TableView<Order> pendingTable;
    @FXML private TableColumn<Order, String> clientNameCol;
    @FXML private TableColumn<Order, String> clientAddressCol;
    @FXML private TableColumn<Order, String> commentsCol;
    @FXML private Label priceLabel, totalLabel, taxLabel;
    @FXML private Button cancelButton, proceedButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuList.setItems(menuCategories);
        
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Product, Number>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Product, Number>("quantity"));
        totalCol.setCellValueFactory(new PropertyValueFactory<Product, Number>("total"));
        orderTable.setPlaceholder(new Label("Προσθέστε προϊόντα!"));
        orderTable.setItems(products);
        
        clientNameCol.setCellValueFactory(new PropertyValueFactory<Order, String>("clientName"));
        clientAddressCol.setCellValueFactory(new PropertyValueFactory<Order, String>("clientAddress"));
        commentsCol.setCellValueFactory(new PropertyValueFactory<Order, String>("comments"));
        pendingTable.setPlaceholder(new Label("Καμία παραγγελία!"));
        pendingTable.setItems(orders);
    }

    @FXML
    private void showOptions(javafx.scene.input.MouseEvent event) {
        String selection = menuList.getSelectionModel().getSelectedItem();
        switch(selection) {
            case "Σαλάτες":
                menuOptions.setItems(salates);
                break;
            case "Ορεκτικά":
                menuOptions.setItems(orektika);
                break;
            case "Της ώρας":
                menuOptions.setItems(tisOras);
                break;
            case "Μαγειρευτά":
                menuOptions.setItems(mageireuta);
                break;
            case "Ψάρια":
                menuOptions.setItems(psaria);
                break;
            case "Επιδόρπια":
                menuOptions.setItems(epidorpia);
                break;
            case "Αναψυκτικά":
                menuOptions.setItems(anapsyktika);
                break;
            case "Μπύρες":
                menuOptions.setItems(mpyres);
                break;
            case "Κρασιά":
                menuOptions.setItems(krasia);
                break;
        }
    }
    

    @FXML
    private void addToTable(javafx.scene.input.MouseEvent event) {
        String selection = menuOptions.getSelectionModel().getSelectedItem();
        switch(selection) {
            case "Μαρούλι":
                marouli=addProduct(marouli, "Μαρούλι", 5.0);
                break;
            case "Μελιτζανοσαλάτα":
                melitzanosalata=addProduct(melitzanosalata, "Μελιτζανοσαλάτα", 6.0);
                break;
            case "Παντζάρι":
                pantzari=addProduct(pantzari, "Παντζάρι", 6.0);
                break;
            case "Ρόκα":
                roka=addProduct(roka, "Ρόκα", 6.5);
                break;
            case "Τζατζίκι":
                tzatziki=addProduct(tzatziki, "Τζατζίκι", 5.5);
                break;
            case "Χτυπητή":
                xtypiti=addProduct(xtypiti, "Χτυπητή", 5.5);
                break;
            case "Χωριάτικη":
                xoriatiki=addProduct(xoriatiki, "Χωριάτικη", 6.0);
                break;
            case "Καλαμαράκια":
                kalamarakia=addProduct(kalamarakia, "Καλαμαράκια", 7.0);
                break;
            case "Κασεροκροκέτες":
                kaserokroketes=addProduct(kaserokroketes, "Κασεροκροκέτες", 4.0);
                break;
            case "Κολοκυθάκια":
                kolokythakia=addProduct(kolokythakia, "Κολοκυθάκια", 4.5);
                break;
            case "Πατάτα φούρνου":
                patataFournou=addProduct(patataFournou, "Πατάτα φούρνου", 4.0);
                break;
            case "Πιπεριές φλωρίνης":
                piperiesFlorinis=addProduct(piperiesFlorinis, "Πιπεριές φλωρίνης", 3.0);
                break;
            case "Χαλούμι σχάρας":
                xaloumiSxaras=addProduct(xaloumiSxaras, "Χαλούμι σχάρας", 5.5);
                break;
            case "Χταπόδι":
                xtapodi=addProduct(xtapodi, "Χταπόδι", 8.0);
                break;
            case "Κεμπάπ":
                kempap=addProduct(kempap, "Κεμπάπ", 7.0);
                break;
            case "Κοντοσούβλι":
                kontosouvli=addProduct(kontosouvli, "Κοντοσούβλι", 7.0);
                break;
            case "Μπιφτέκι":
                mpifteki=addProduct(mpifteki, "Μπιφτέκι", 6.5);
                break;
            case "Μπιφτέκι γεμιστό":
                mpiftekiGemisto=addProduct(mpiftekiGemisto, "Μπιφτέκι γεμιστό", 7.0);
                break;
            case "Μπριζόλα χοιρινή":
                mprizolaXoirini=addProduct(mprizolaXoirini, "Μπριζόλα χοιρινή", 6.5);
                break;
            case "Γύρος χοιρινός":
                gyrosXoirinos=addProduct(gyrosXoirinos, "Γύρος χοιρινός", 6.5);
                break;
            case "Γύρος κοτόπουλο":
                gyrosKotopoulo=addProduct(gyrosKotopoulo, "Γύρος κοτόπουλο", 6.0);
                break;
            case "Παϊδάκια":
                paidakia=addProduct(paidakia, "Παϊδάκια", 7.5);
                break;
            case "Σουβλάκι χοιρινό":
                souvlakiXoirino=addProduct(souvlakiXoirino, "Σουβλάκι χοιρινό", 6.5);
                break;
            case "Σουβλάκι κοτόπουλο":
                souvlakiKotopoulo=addProduct(souvlakiKotopoulo, "Σουβλάκι κοτόπουλο", 6.5);
                break;
            case "Φιλέτο κοτόπουλο":
                filetoKotopoulo=addProduct(filetoKotopoulo, "Φιλέτο κοτόπουλο", 7.5);
                break;
            case "Ψαρονέφρι":
                psaronefri=addProduct(psaronefri, "Ψαρονέφρι", 8.0);
                break;
            case "Αρνί":
                arni=addProduct(arni, "Αρνί", 9.5);
                break;
            case "Κότσι":
                kotsi=addProduct(kotsi, "Κότσι", 8.0);
                break;
            case "Λαχανοντολμάδες":
                laxanontolmades=addProduct(laxanontolmades, "Λαχανοντολμάδες", 5.5);
                break;
            case "Μουσακάς":
                mousakas=addProduct(mousakas, "Μουσακάς", 6.5);
                break;
            case "Παπουτσάκια":
                papoutsakia=addProduct(papoutsakia, "Παπουτσάκια", 6.0);
                break;
            case "Σουτζουκάκια":
                soutzoukakia=addProduct(soutzoukakia, "Σουτζουκάκια", 6.5);
                break;
            case "Γαρίδες":
                garides=addProduct(garides, "Γαρίδες", 11.0);
                break;
            case "Γαύρος":
                gauros=addProduct(gauros, "Γαύρος", 7.5);
                break;
            case "Λαυράκι":
                lauraki=addProduct(lauraki, "Λαυράκι", 10.0);
                break;
            case "Μπακαλιάρος":
                mpakaliaros=addProduct(mpakaliaros, "Μπακαλιάρος", 9.0);
                break;
            case "Μύδια":
                mydia=addProduct(mydia, "Μύδια", 8.5);
                break;
            case "Σαρδέλα":
                sardela=addProduct(sardela, "Σαρδέλα", 8.0);
                break;
            case "Τσιπούρα":
                tsipoura=addProduct(tsipoura, "Τσιπούρα", 9.0);
                break;
            case "Γαλακτομπούρεκο":
                galaktompoureko=addProduct(galaktompoureko, "Γαλακτομπούρεκο", 4.5);
                break;
            case "Καταϊφι":
                kataifi=addProduct(kataifi, "Καταϊφι", 4.0);
                break;
            case "Μπακλαβάς":
                mpaklavas=addProduct(mpaklavas, "Μπακλαβάς", 4.5);
                break;
            case "Χαλβάς":
                xalvas=addProduct(xalvas, "Χαλβάς", 3.5);
                break;
            case "Coca Cola":
                cocaCola=addProduct(cocaCola, "Coca Cola", 3.5);
                break;
            case "Fanta":
                fanta=addProduct(fanta, "Fanta", 3.5);
                break;
            case "Sprite":
                sprite=addProduct(sprite, "Sprite", 3.0);
                break;
            case "Σουρωτή":
                souroti=addProduct(souroti, "Σουρωτή", 2.5);
                break;
            case "Amstel":
                amstel=addProduct(amstel, "Amstel", 4.0);
                break;
            case "Heineken":
                heineken=addProduct(heineken, "Heineken", 4.5);
                break;
            case "Mythos":
                mythos=addProduct(mythos, "Mythos", 4.0);
                break;
            case "Fix":
                fix=addProduct(fix, "Fix", 3.5);
                break;
            case "Κόκκινο":
                kokkino=addProduct(kokkino, "Κόκκινο", 18.5);
                break;
            case "Λευκό":
                leuko=addProduct(leuko, "Λευκό", 22.0);
                break;
            case "Ροζέ":
                roze=addProduct(roze, "Ροζέ", 20.5);
                break;
        }
    }
    
    public Product addProduct(Product product, String name, Double price) {
        if(!exists(product)) {
            product = new Product(name, price);
            products.add(product);
        }
        else {
            product.setQuantity(product.getQuantity()+1);
            orderTable.refresh();
        }
        sum +=product.getTotal();
        priceLabel.setText(String.valueOf(sum));
        totalLabel.setText(String.valueOf(Math.round((sum+sum*0.24)*100.0)/100.0));
        return product;
    }

    @FXML
    private void addToOrder(MouseEvent event) {
        if(nameField.getText().equals("") || addressField.getText().equals("")) {
            Alert alert = new Alert(AlertType.ERROR, "Ορίστε όνομα και διεύθυνση!", ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK)
                alert.close();
        }
        else {
            if(products.isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR, "Προσθέστε προϊόντα!", ButtonType.OK);
                alert.showAndWait();
                if(alert.getResult() == ButtonType.OK)
                    alert.close();
            }
            else {
                Order order = new Order();
                order.setProducts(products.stream().collect(Collectors.toList()));
                order.setClientName(nameField.getText());
                order.setClientAddress(addressField.getText());
                order.setComments(commentsField.getText());
                order.setTotalPrice(Double.parseDouble(totalLabel.getText()));
                orders.add(order);
                orderTable.getItems().clear();
                nameField.clear();
                addressField.clear();
                commentsField.clear();
                priceLabel.setText("");
                totalLabel.setText("");
                sum=0.0;
            }
        }
    }
    
    @FXML
    private void cancelOrder(MouseEvent event) {
        orderTable.getItems().clear();
        nameField.clear();
        addressField.clear();
        commentsField.clear();
        priceLabel.setText("");
        totalLabel.setText("");
        sum=0.0;
    }

    @FXML
    private void openOrder(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLOrder.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FXMLOrderController display = fxmlLoader.getController();
        
        display.setProductList(pendingTable.getSelectionModel().getSelectedItem());
        display.selectedOrderPosition(pendingTable.getSelectionModel().getSelectedIndex());
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Παραγγελίες");
        stage.setResizable(false);
        stage.show();
    }
    
    public boolean exists(Product p) {
        for(int i=0; i<products.size(); i++) {
            if(products.get(i).equals(p))
                return true;
        }
        return false;
    }

    public static ObservableList<Order> getOrders() {
        return orders;
    }
    
}

