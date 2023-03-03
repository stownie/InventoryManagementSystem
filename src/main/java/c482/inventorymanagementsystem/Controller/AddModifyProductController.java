package c482.inventorymanagementsystem.Controller;

import c482.inventorymanagementsystem.Model.Inventory;
import c482.inventorymanagementsystem.Model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddModifyProductController {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    @FXML
    protected Button save;
    @FXML
    protected TextField idField;
    @FXML
    protected TextField nameField;
    @FXML
    protected TextField inventoryField;
    @FXML
    protected TextField priceField;
    @FXML
    protected TextField maxField;
    @FXML
    protected TextField minField;

    public void onSaveButtonClick(){
        id = Integer.parseInt(idField.getText());
        name = nameField.getText();
        price = Double.parseDouble(priceField.getText());
        stock = Integer.parseInt(inventoryField.getText());
        max = Integer.parseInt(maxField.getText());
        min = Integer.parseInt(minField.getText());
        Product product = new Product(id, name, price, stock, max, min);
        Inventory.addProduct(product);
        System.out.println(id);
        System.out.println(name);
        System.out.println(price);
        System.out.println(stock);
        System.out.println(max);
        System.out.println(min);
        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }
}
