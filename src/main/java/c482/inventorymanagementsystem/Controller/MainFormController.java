package c482.inventorymanagementsystem.Controller;

import c482.inventorymanagementsystem.InventoryApplication;
import c482.inventorymanagementsystem.Model.Inventory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

   // public void start(Stage stage) throws IOException {
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
    public void onAddPartButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("addModifyPart-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 825, 400);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onModifyPartButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("addModifyPart-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 825, 400);
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onAddProductButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("addModifyProduct-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 825, 700);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onModifyProductButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("addModifyProduct-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 825, 700);
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }
}