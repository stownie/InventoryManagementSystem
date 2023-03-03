package c482.inventorymanagementsystem.Controller;

import c482.inventorymanagementsystem.Model.Inventory;
import c482.inventorymanagementsystem.Model.Part;
import c482.inventorymanagementsystem.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddModifyProductController implements Initializable {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private ObservableList<Part> associateParts = FXCollections.observableArrayList();
    @FXML
    public Button save;
    @FXML
    public Button cancel;
    @FXML
    public Button add;
    @FXML
    public Button removeAssociatedPart;
    @FXML
    public TextField idField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField inventoryField;
    @FXML
    public TextField priceField;
    @FXML
    public TextField maxField;
    @FXML
    public TextField minField;
    @FXML
    public TextField searchField;
    @FXML
    public TableView allParts;
    @FXML
    public TableView associatedParts;
    @FXML
    public TableColumn allId;
    @FXML
    public TableColumn allName;
    @FXML
    public TableColumn allInv;
    @FXML
    public TableColumn allPrice;
    @FXML
    public TableColumn assId;
    @FXML
    public TableColumn assName;
    @FXML
    public TableColumn assInv;
    @FXML
    public TableColumn assPrice;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        allParts.setItems(Inventory.getAllParts());
        allId.setCellValueFactory(new PropertyValueFactory<>("id"));
        allName.setCellValueFactory(new PropertyValueFactory<>("name"));
        allInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedParts.setItems(associateParts);
        assId.setCellValueFactory(new PropertyValueFactory<>("id"));
        assName.setCellValueFactory(new PropertyValueFactory<>("name"));
        assInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    public void onSaveButtonClick(){
        id = Integer.parseInt(idField.getText());
        name = nameField.getText();
        price = Double.parseDouble(priceField.getText());
        stock = Integer.parseInt(inventoryField.getText());
        max = Integer.parseInt(maxField.getText());
        min = Integer.parseInt(minField.getText());
        Product product = new Product(id, name, price, stock, max, min);
        Inventory.addProduct(product);

        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }
}
