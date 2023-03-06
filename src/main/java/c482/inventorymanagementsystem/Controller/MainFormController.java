package c482.inventorymanagementsystem.Controller;

import c482.inventorymanagementsystem.InventoryApplication;
import c482.inventorymanagementsystem.Model.Inventory;
import c482.inventorymanagementsystem.Model.Part;
import c482.inventorymanagementsystem.Model.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

public class MainFormController implements Initializable {
    @FXML
    public Button addPart;
    @FXML
    public Button modifyPart;
    @FXML
    public Button deletePart;
    @FXML
    public Button addProduct;
    @FXML
    public Button modifyProduct;
    @FXML
    public Button deleteProduct;
    @FXML
    public TableView partsTable;
    @FXML
    public TableColumn partIdColumn;
    @FXML
    public TableColumn partNameColumn;
    @FXML
    public TableColumn partInvColumn;
    @FXML
    public TableColumn partPriceColumn;
    @FXML
    public TableView productTable;
    @FXML
    public TableColumn productIdColumn;
    @FXML
    public TableColumn productNameColumn;
    @FXML
    public TableColumn productInvColumn;
    @FXML
    public TableColumn productPriceColumn;
    @FXML
    public Button exit;
    @FXML
    public TextField searchPartField;
    @FXML
    public TextField searchProductField;
    @FXML
    public Label noPartResult;
    @FXML
    public Label noProductResult;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    partsTable.setItems(Inventory.getAllParts());
    partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
    partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    productTable.setItems(Inventory.getAllProducts());
    productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    productInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
    productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    @FXML
    public void onAddPartButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("addModifyPart-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 825, 400);
        AddModifyPartController controller = fxmlLoader.getController();
        controller.autoGenerateID();
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void onModifyPartButtonClick(ActionEvent event) throws IOException {
        try {
            Part part = (Part) partsTable.getSelectionModel().getSelectedItem();

            FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("addModifyPart-view.fxml"));
            fxmlLoader.load();

            AddModifyPartController controller = fxmlLoader.getController();
            controller.sendPart(part);

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = fxmlLoader.getRoot();

            stage.setTitle("Modify Product");
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modify Error");
            alert.setHeaderText("Modify Error");
            alert.setContentText("You must select a Product before modifying");
            alert.showAndWait();
        }
    }
    @FXML
    public void onDeletePartButtonClick(ActionEvent event) {
        Part part = (Part) partsTable.getSelectionModel().getSelectedItem();
        if (part != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Part Delete");
            alert.setHeaderText("Confirm Part Delete");
            alert.setContentText("Are you sure you want to delete this part? This action cannot be undone.");
            Optional<ButtonType> result = alert.showAndWait();
            if (((Optional<?>) result).get() == ButtonType.OK) {
                Inventory.deletePart(part);
            }
            else {
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete Error");
            alert.setHeaderText("Delete Error");
            alert.setContentText("You must select a Part before deleting.");
            alert.showAndWait();
        }
    }
    @FXML
    public void onAddProductButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("addModifyProduct-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 825, 700);
        AddModifyProductController controller = fxmlLoader.getController();
        controller.autoGenerateID();
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) addProduct.getScene().getWindow();
        stage1.close();
    }
    @FXML
    public void onModifyProductButtonClick(ActionEvent event) throws IOException {
        try {
            Product product = (Product) productTable.getSelectionModel().getSelectedItem();

            FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("addModifyProduct-view.fxml"));
            fxmlLoader.load();

            AddModifyProductController controller = fxmlLoader.getController();
            controller.sendProduct(product);

            Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            Parent scene = fxmlLoader.getRoot();

            stage.setTitle("Modify Product");
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modify Error");
            alert.setHeaderText("Modify Error");
            alert.setContentText("You must select a Product before modifying");
            alert.showAndWait();
        }
    }
    @FXML
    public void onDeleteProductButtonClick(ActionEvent event) {
        Product product = (Product) productTable.getSelectionModel().getSelectedItem();
        if (product != null) {
            ObservableList<Part> productParts = product.getAllAssociatedParts();
            if (productParts.size() < 1) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Product Delete");
                alert.setHeaderText("Confirm Product Delete");
                alert.setContentText("Are you sure you want to delete this product? This action cannot be undone.");
                Optional<ButtonType> result = alert.showAndWait();
                if (((Optional<?>) result).get() == ButtonType.OK) {
                    Inventory.deleteProduct(product);
                }
                else {}
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Product Delete Error");
                alert.setHeaderText("Product Has Associated Parts");
                alert.setContentText("Products with Associated Parts can not be deleted.");
                Optional<ButtonType> result = alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete Error");
            alert.setHeaderText("Delete Error");
            alert.setContentText("You must select a Product before deleting.");
            alert.showAndWait();
        }
    }
    @FXML
    public void onExitButtonClick(ActionEvent event){
        System.exit(0);
    }
    @FXML
    public void onPartSearch(KeyEvent event){
        String partName = searchPartField.getText();
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
        partsTable.setItems(parts);

        if (parts.size() == 0) {
            noPartResult.setText("Search Returned No Results");
        } else {
            noPartResult.setText(null);
        }
    }
    @FXML
    public void onProductSearch(KeyEvent event){
        String productName = searchProductField.getText();
        ObservableList<Product> products = lookupProduct(productName);
        if (products.size() == 0) {
            try {
                int productId = Integer.parseInt(productName);
                Product product = lookupProduct(productId);
                if (product != null)
                    products.add(product);
            } catch (NumberFormatException e) {
                //ignore
            }
        }
        productTable.setItems(products);

        if (products.size() == 0) {
            noProductResult.setText("Search Returned No Results");
        } else {
            noProductResult.setText(null);
        }
    }
}
