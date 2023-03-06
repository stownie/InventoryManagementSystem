package c482.inventorymanagementsystem.Controller;

import c482.inventorymanagementsystem.InventoryApplication;
import c482.inventorymanagementsystem.Model.Inventory;
import c482.inventorymanagementsystem.Model.Part;
import c482.inventorymanagementsystem.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static c482.inventorymanagementsystem.Model.Inventory.lookupPart;
import static c482.inventorymanagementsystem.Model.Inventory.lookupProduct;

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
    public Label noPartResult;
    @FXML
    public TableView<Part> allParts;
    @FXML
    public TableView<Part> associatedParts;
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

    public void onSaveButtonClick() throws IOException {
        id = Integer.parseInt(idField.getText());
        name = nameField.getText();
        price = Double.parseDouble(priceField.getText());
        stock = Integer.parseInt(inventoryField.getText());
        max = Integer.parseInt(maxField.getText());
        min = Integer.parseInt(minField.getText());
        associateParts = associatedParts.getItems();
        if(max >= min && stock >= min && stock <= max ) {
            Product product = new Product(associateParts, id, name, price, stock, max, min);

            if (Inventory.lookupProduct(id) == null) {
                Inventory.addProduct(product);
            } else {
                Inventory.updateProduct(id, product);
            }
            Stage stage = (Stage) save.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 400);
            stage.setTitle("Inventory Management Application");
            stage.setScene(scene);
            stage.show();
        }
        else if (min > max){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User input error");
            alert.setHeaderText("Min/Max Error");
            alert.setContentText("Max inventory must be greater than min inventory.");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User input error");
            alert.setHeaderText("Stock Error");
            alert.setContentText("Your inventory level must be between min and max inventory.");
            alert.showAndWait();
        }
    }
    public void onCancelButtonClick() throws IOException {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("Inventory Management Application");
        stage.setScene(scene);
        stage.show();
    }

    public void onRemoveAssociatedPartsButtonClick(){
        Part part = associatedParts.getSelectionModel().getSelectedItem();
        associateParts = associatedParts.getItems();
        if (part != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Removal of Associated Part");
            alert.setHeaderText("Confirm Removal of Associated Part");
            alert.setContentText("Are you sure you want to remove this Associated Part? This action cannot be undone.");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).get() == ButtonType.OK) {
                associateParts.remove(part);
                associatedParts.setItems(associateParts);
            }
            else {}
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Remove Part Error");
            alert.setHeaderText("Remove Part Error");
            alert.setContentText("You must select a Part before removing from Product");
            alert.showAndWait();
        }
    }
    public void onAddButtonClick(){
        Part part = allParts.getSelectionModel().getSelectedItem();
        associateParts = associatedParts.getItems();
        if (part != null) {
            if (!associateParts.contains(part)) {
                associateParts.add(part);
                associatedParts.setItems(associateParts);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Part Error");
                alert.setHeaderText("Add Part Error");
                alert.setContentText("This part has already been added to this product.");
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Part Error");
            alert.setHeaderText("Add Part Error");
            alert.setContentText("You must select a Part before adding to Product");
            alert.showAndWait();
        }

    }
    protected void autoGenerateID(){
        int i = 1;
        while (Inventory.lookupProduct(i) != null){
            ++i;
        }
        idField.setText(String.valueOf(i));
    }
    public void sendProduct(Product product){
        idField.setText(String.valueOf(product.getId()));
        nameField.setText(product.getName());
        inventoryField.setText(String.valueOf(product.getStock()));
        priceField.setText(String.valueOf(product.getPrice()));
        maxField.setText(String.valueOf(product.getMax()));
        minField.setText(String.valueOf(product.getMin()));
        associatedParts.setItems(product.getAllAssociatedParts());

    }
    public void onPartSearch(KeyEvent event){
        String partName = searchField.getText();
        ObservableList<Part> parts = lookupPart(partName);
        if (parts.size() == 0) {
            try {
                int partId = Integer.parseInt(partName);
                Part part = lookupPart(partId);
                if (part != null)
                    parts.add(part);
            } catch (NumberFormatException e) {
                //ignore
            }
        }
        allParts.setItems(parts);

        if (parts.size() == 0) {
            noPartResult.setText("Search Returned No Results");
        } else {
            noPartResult.setText(null);
        }
    }
}
